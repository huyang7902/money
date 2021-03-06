CREATE TABLE `settlement_history` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TOTLE_MONEY` decimal(10,2) DEFAULT NULL COMMENT '总计',
  `AVG_MONEY` decimal(10,2) DEFAULT NULL COMMENT '平均',
  `DETAIL` varchar(200) DEFAULT NULL COMMENT '详细',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(200) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8