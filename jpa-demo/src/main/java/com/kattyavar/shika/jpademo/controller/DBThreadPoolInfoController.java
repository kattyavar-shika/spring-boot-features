package com.kattyavar.shika.jpademo.controller;

import com.kattyavar.shika.jpademo.model.Employee;
import com.kattyavar.shika.jpademo.repository.EmployeeRepository;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class DBThreadPoolInfoController {


  @Autowired
  private HikariDataSource dataSource;

  @Autowired
  EmployeeRepository employeeRepository;

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


  @GetMapping("/dumprecord/{id}")
  public Employee dumpRecord(@PathVariable Integer id) {
    Employee employee = new Employee();

    if (id != 1) {
      log.info("Setting the id ");
      employee.setId(Long.valueOf(id));
    }
    employee.setName(" Name " + id);
    employee.setAddress(" address for " + id);
    log.info(" emp object as {} ", employee);
    return employeeRepository.save(employee);
  }

}
