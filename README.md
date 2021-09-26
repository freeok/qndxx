# 青年大学习截图上交系统

#### 介绍

1. 简化了青年大学习截图上交的流程
2. 班级成员仅需上传截图即可，无需手动加文字、手动调整截图大小
3. 班级负责人可以一键下载截图压缩包、查看上交情况等

### 首页

![输入图片说明](https://images.gitee.com/uploads/images/2021/0919/183928_b92a73e2_5591048.png "屏幕截图.png")

### 上传界面

![输入图片说明](https://images.gitee.com/uploads/images/2021/0713/233411_e65eb708_5591048.png "屏幕截图.png")

### 上传成功界面

![输入图片说明](https://images.gitee.com/uploads/images/2021/0919/184133_3523c180_5591048.png "屏幕截图.png")

### 后台首页

![后台首页](https://images.gitee.com/uploads/images/2021/0429/202107_ad92149d_5591048.png "屏幕截图.png")

#### 软件架构

软件架构说明

后端：

1. JDK8
2. SpringBoot2.4.1
3. MySQL8
4. Mybatis、分页助手pagehelper
5. API文档使用Swagger3(http://localhost:8080/swagger-ui/index.html)

#### 安装教程
小白请看，本地运行首先得有以下环境
1. jdk8+
2. maven
3. mysql8+ & mysql图形管理工具（建议）

1. 执行sql脚本
2. 分别在student和clazz表中导入班级成员信息和班级信息
3. 修改application-dev.yml
4. Dockerfile已配好

#### 使用说明

1. 导入班级相关数据，根据student表的role字段设置管理员和密码
1. 管理员登录后台，设置青年大学习的季数、期数
2. 收齐后下载即可，然后关闭系统（关闭系统后所有成员无法上传）
3. 使用前重置提交（删除往期截图）并开启系统
4. 非管理员进入后台登录页面前需输入口令，默认为8848，可在uploda.html页面386行作用修改js代码，结合数据库请自行扩展

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5. Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
