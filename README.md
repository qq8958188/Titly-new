# Titly-new
通用架构

Android APP架构

整体架构讲解：

业务层：采用MVP的架构模型 View：直接是Android的各种view Presenter：一般代表fragment Model：数据模型

业务层采用一个Activity，配合多个fragment的形式，提高架构的性能，并且通过url导航的形式， 对各fragment页面，service，broadReceiver等进行解耦。

架构层：

包含两个中间件：数据服务和通道服务

数据服务中间件会对上层暴露数据服务层，由数据服务中间件来对数据进行统一管理 包含rpc和dl两种服务

通道服务架构待定
