package com.kattyavar.shika.users_service.service;

import com.kattyavar.shika.users_service.model.UserInfo;
import com.kattyavar.shika.users_service.model.UserOrderResponse;
import com.kattyavar.shika.users_service.service.integeration.OrderIntegration;
import com.kattyavar.shika.users_service.service.integeration.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

  private Map<String, UserInfo> dummyData;

  @Autowired
  OrderIntegration orderIntegration;

  public UserServiceImpl() {
    this.dummyData = new HashMap<>();
    generateInitData();
  }

  @Override
  public UserOrderResponse getUserOrdersByUserId(String userId) {

    //Find the user.
    UserInfo userInfo = dummyData.get(userId);

    if (userInfo != null) {
      // If we get the data from orders.
      /**
       * Here you can decide.
       * If you want to check CircuitBreaker then make call to getOrdersByUserId
       * if you want to check Retry then make call to getOrdersByUserIdWithReTry
       */
      OrderResponse orderResponseInt = orderIntegration.getOrdersByUserId(userId);
      //OrderResponse orderResponseInt = orderIntegration.getOrdersByUserIdWithReTry(userId);
      return new UserOrderResponse(userInfo, orderResponseInt.orders());
    }
    return new UserOrderResponse(userInfo, null);
  }


  private void generateInitData() {

    dummyData.put("1", generateUserDummyData("1"));
    dummyData.put("2", generateUserDummyData("2"));
    dummyData.put("3", generateUserDummyData("3"));
    dummyData.put("4", generateUserDummyData("4"));
  }

  private UserInfo generateUserDummyData(String userId) {
    String firstName = "First name for - " + userId;
    String lastName = "Last name for - " + userId;
    String email = "Email for - " + userId;
    return new UserInfo(userId, firstName, lastName, email);
  }
}
