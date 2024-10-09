package com.kattyavar.shika.jpademo.controller;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DBThreadPoolInfoController {


  @Autowired
  private HikariDataSource dataSource;

  /*
  If you would like to override this information,
  configure as below,
     hikari:
      maximum-pool-size: 10                   # Maximum number of connections in the pool
      minimum-idle: 5                         # Minimum number of idle connections
      idle-timeout: 600000                    # Idle time before a connection is removed (in milliseconds)
      connection-timeout: 30000               # Maximum time to wait for a connection (in milliseconds)
   */

  @GetMapping("/connection-pool-metrics")
  public String getConnectionPoolMetrics() {

    return String.format("Active connections %d, Total Connection: %d, Max Connection: %d," +
        " connection wait time: %d  ",
      dataSource.getHikariPoolMXBean().getActiveConnections(),
      dataSource.getHikariPoolMXBean().getTotalConnections(),
      dataSource.getMaximumPoolSize(),
      dataSource.getConnectionTimeout());
  }


}
