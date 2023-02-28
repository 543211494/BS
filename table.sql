create database education;
use education;
DROP TABLE IF EXISTS `user`;
create table user
(
    `uid`           int primary key auto_increment COMMENT '用户id',
    `password`      varchar(16)   not null COMMENT '用户密码',
    `email`         varchar(16) not null COMMENT '用户邮箱',
    `power`         varchar(16)   not null COMMENT '用户权限',
    `identity`      varchar(32) unique  not null COMMENT '用户身份证号',
    `mid`           int           COMMENT '用户所属专业id',
    `fullName`      varchar(16)   not null COMMENT '用户姓名',
    `studentId`     varchar(16)   COMMENT '用户学号',
    `age`           int           COMMENT '用户年龄',
    `sex`           varchar(4)    not null COMMENT '用户性别',
    `qualification` int default 0 COMMENT '用户保研资格',
    `year`          int  COMMENT '资格对应年份',
    `isDelete`    int           not null COMMENT '是否删除'
) COMMENT '用户';

DROP TABLE IF EXISTS `major`;
create table major
(
    `mid`        int primary key auto_increment COMMENT '专业id',
    `code` varchar(8) not null COMMENT '专业招生代码',
    `university` varchar(32) not null COMMENT '专业所属大学',
    `college`    varchar(32) not null COMMENT '专业所属学院',
    `majorName`  varchar(32) not null COMMENT '专业名称',
    `level`      varchar(4)  not null COMMENT '专业学科评级',
    `isDelete`    int           not null COMMENT '是否删除'
) COMMENT '专业';

DROP TABLE IF EXISTS `plan`;
create table plan
(
    `pid`    int primary key auto_increment COMMENT '招生计划id',
    `mid`    int not null COMMENT '专业id',
    `numberA` int not null COMMENT '学硕招生人数',
    `numberB` int not null COMMENT '专硕招生人数',
    `numberC` int not null COMMENT '直博招生人数',
    `number` int not null COMMENT '总招生人数',
    `year`   int not null COMMENT '招生年份',
    `isDelete`    int           not null COMMENT '是否删除'
) COMMENT '招生计划';

DROP TABLE IF EXISTS `notice`;
create table notice
(
    `nid`         int primary key auto_increment COMMENT '公告id',
    `content`     varchar(1024) not null COMMENT '公告内容',
    `publishTime` datetime      not null COMMENT '发布时间',
    `uid`         int           not null COMMENT '发布者id',
    `isDelete`    int           not null COMMENT '是否删除'
) COMMENT '公告';

DROP TABLE IF EXISTS `message`;
create table message
(
    `mid`         int primary key auto_increment COMMENT '消息id',
    `content`     varchar(1024) not null COMMENT '消息内容',
    `publishTime` datetime      not null COMMENT '发布时间',
    `hasReaded`   int           not null COMMENT '是否已读',
    `uid`         int           not null COMMENT '接收者id',
    `isDelete`    int           not null COMMENT '是否删除'
) COMMENT '消息';

DROP TABLE IF EXISTS `board`;
create table board
(
    `bid`         int primary key auto_increment COMMENT '留言id',
    `content`     varchar(1024) not null COMMENT '留言内容',
    `publishTime` datetime      not null COMMENT '发布时间',
    `uid`         int           not null COMMENT '发布者id',
    `pid`         int           not null COMMENT '父留言id',
    `isDelete`    int           not null COMMENT '是否删除'
) COMMENT '留言';

DROP TABLE IF EXISTS `registration`;
create table registration
(
    `rid`      int primary key auto_increment COMMENT '报名表id',
    `uid`      int          null COMMENT '填报人账号id',
    `photo`    varchar(128)  COMMENT '填报人照片链接',
    `address`  varchar(128) not null COMMENT '填报人联系地址',
    `phone`    varchar(16)  not null COMMENT '填报人联系电话',
    `current` int not null COMMENT '当前处理到的志愿',
    `year`     int not null COMMENT '填报年份',
    `type`    int not null COMMENT '报考类型',
    `status`   int not null COMMENT '报名状态，0未录取，1已录取，2所有志愿已处理完但未录取',
    `isDelete` int not null COMMENT '是否删除'
) COMMENT '报名表';

DROP TABLE IF EXISTS `volunteer`;
create table volunteer
(
    `vid`      int primary key auto_increment COMMENT '志愿id',
    `rank`   int           not null COMMENT '第几志愿',
    `mid`      int           not null COMMENT '志愿对应的专业id',
    `rid`      int           not null COMMENT '志愿所属报名表id',
    `firstGrade`      int           not null COMMENT '初试得分',
    `secondGrade`      int           not null COMMENT '复试得分',
    `finalGrade`      int           not null COMMENT '最终得分',
    `type`       int        not null COMMENT '招生类型,0学，1专，2直',
    `isDelete` int default 0 not null COMMENT '是否删除'
) COMMENT '志愿';

DROP TABLE IF EXISTS `admission`;
create table admission
(
    `aid`      int primary key auto_increment COMMENT '录取id',
    `uid`      int        not null COMMENT '被录取人id',
    `mid`      int COMMENT '录取专业',
    `year`     int not null COMMENT '录取年份',
    `type`       int        not null COMMENT '招生类型,0学，1专，2直',
    `isDelete` int        not null COMMENT '是否删除'
) COMMENT '录取';