package com.my.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.result.UserResult;
import com.my.service.OrderService;
import com.my.service.UserService;

import io.swagger.annotations.Api;

class UserCommand {
    String startDate;
    String endDate;
    Date startDateDate;
    Date endDateDate;
    long from;
    long offset;

    UserCommand(String startDate, String endDate, long from, long offset) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.from = from;
        this.offset = offset;
    }
}

class MyControllerDateException extends Exception {

    private static final long serialVersionUID = -434055439175456378L;

    public MyControllerDateException(String msg) {
        super(msg);
    }
}

class MyControllerLongException extends Exception {

    private static final long serialVersionUID = 7585571007858240249L;

    public MyControllerLongException(String msg) {
        super(msg);
    }
}

class MyControllerNotLoginException extends Exception {
    public MyControllerNotLoginException() {
        super();
    }
}

@RestController
@RequestMapping("/")
@Api(tags = { "user" }, description = "Information about the user")
public class UserController {

    private static final String USER = "user";
    private static final String USERS = "users";
    private static final String ORDER = "order";
    private static final String DATE_DASH_YYYYMMDD = "yyyy-MM-dd";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_DASH_YYYYMMDD);
    private static final long MILLSECONDS_OF_DAY = 24 * 60 * 60 * 1000;
    private static final long MILLSECONDS_OF_MONTH = 30 * MILLSECONDS_OF_DAY;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = USER + "/{id}", method = RequestMethod.GET)
    public UserResult get(@PathVariable long id) {
        return userService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = USERS, method = RequestMethod.GET)
    public List<UserResult> getUserListByTimeRange(
            @RequestParam(value = "startdate", required = false) String startDate,
            @RequestParam(value = "enddate", required = false) String endDate,
            @RequestParam(required = false, defaultValue = "0") long from,
            @RequestParam(required = false, defaultValue = "20") long offset)
            throws ParseException, MyControllerDateException, MyControllerLongException {

        UserCommand command = new UserCommand(startDate, endDate, from, offset);
        processGetUserListByTimeRangeParams(command);
        return userService.getUserListByTimeRange(command.startDateDate, command.endDateDate, command.from,
                command.offset);
    }

    @RequestMapping(value = ORDER, method = RequestMethod.PUT)
    public void newOrder(@RequestParam(value = "userId", required = true) long userId,
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "productsWithCount", required = true) String productsWithCount)
            throws MyControllerNotLoginException {
        if (!isLogin(userId, token)) {
            throw new MyControllerNotLoginException();
        }
        Map<Long, Integer> productMaps = processProductMaps(productsWithCount);
        orderService.newOrder(userId, productMaps);
    }

    private Map<Long, Integer> processProductMaps(String productsWithCount) {
        Map<Long, Integer> productMaps = new HashMap<Long, Integer>();
        String[] productPairs = productsWithCount.split(",");
        for (int i = 0; i < productPairs.length; i++) {
            long productId = Long.parseLong(productPairs[i].split(":")[0]);
            int count = Integer.parseInt(productPairs[i].split(":")[1]);
            productMaps.put(productId, count);
        }
        return productMaps;
    }

    private boolean isLogin(long userId, String token) {
        return true;
    }

    private void processGetUserListByTimeRangeParams(UserCommand command)
            throws ParseException, MyControllerDateException, MyControllerLongException {
        Date currentDate = new Date();
        long days30Before = currentDate.getTime() - MILLSECONDS_OF_MONTH;
        command.startDateDate = command.startDate == null ? new Date(days30Before) : sdf.parse(command.startDate);
        command.endDateDate = command.endDate == null ? currentDate : sdf.parse(command.endDate);
        if (command.startDateDate.getTime() > command.endDateDate.getTime()) {
            throw new MyControllerDateException("start date should before end date");
        }
        if (command.from < 0 || command.offset < 0) {
            throw new MyControllerLongException("from or offset should be greater than 0");
        }

    }

}
