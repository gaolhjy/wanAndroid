鸿洋大神玩安卓开发接口练手项目.

GitHub上已经有很多WanAndroid的项目了,为什么还要有这一个?

(1)一是我自己用于公司商业项目的试水,一些之前没有用过的技术,直接使用到公司项目中,风险太大,而玩安卓项目功能相对较全,并且有免费的接口文档.

(2) 部分项目(指GitHub上的玩安卓项目.下同)使用了dagger2.其实我个人好几次尝试使用dagger2,但都是从入门到放弃了. 这个项目未使用dagger2. 适合同样对dagger2不感冒的人群.部分项目采用了kotlin,但部分人还不会kotlin语法.

(3) 部分人对单Activity+ 多Fragment 的结构不感冒,本项目采用的是自己最喜欢的一半只有tablayout下才使用fragment的编程习惯(估计很多人也是这样).

(4) 部分项目功能不齐全.比如没有新增的todo功能.部分项目对部分异常情况没有处理(比如空数据,比如列表类更多异常).而笔者的项目加入了功能.



使用的开源技术(蓝色字体含链接):
====
整体上是:  Rxjava2+ Retrofit2+ MVP + Glide

1.[轮播图](https://github.com/youth5201314/banner)

2.[下拉刷新、加载更多]

3.Gson解析

4.[butterknife](https://github.com/JakeWharton/butterknife)

5.[eventBus](https://github.com/greenrobot/EventBus)

6.[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

7.[Glide](https://github.com/bumptech/glide)



基本封装与自定义:
====

1.ToolBar的封装

2.baseActivity、baseFragment、baseView、basePresenter

3.统一异常处理的封装

4.Glide的封装

5.日志、sp、屏幕、toast工具类封装



修改完善记录:
====
版本:1.0.0  基本功能完成


项目谈论群:
====

说明:之所以没有使用微信群还是QQ群,主要基于两个原因:
(1)QQ群可以屏蔽,这样大家根据情况进行选择,以便更高效率工作、学习.
(2)QQ群上传资料后,便于保存与查找,也可以临时性和群里其他人私聊.

一共有2个QQ群,
一个是免费的,主要用于大家对玩安卓项目项目(Android端)进行交流;
一个是付费的.我也有自己的工作和娱乐时间，只有大家理解和支持我，我才能专心的为大家解决问题。不过不用担心，如果大家介意知识付费的话,可以加入免费交流群.


QQ群号:156250233 (付费群)

![Image text](https://github.com/gaolhjy/enjoyshop/blob/master/screenshots/QQ%E7%BE%A4(%E4%BB%98%E8%B4%B9).png)



QQ群号: 120798193 (免费交流群. 只有1个要求,先对项目star后,即可添加)

![Image text](https://github.com/gaolhjy/enjoyshop/blob/master/screenshots/QQ%E7%BE%A4(%E5%85%8D%E8%B4%B9).png)



特别感谢:
====
1. [鸿洋大神的开发api](https://www.wanandroid.com/blog/show/2)

2. [主要参考项目] (https://github.com/JsonChao/Awesome-WanAndroid)



关于我:
====

[![Wercker](https://img.shields.io/badge/%E5%85%B3%E4%BA%8E%E6%88%91-CSDN-brightgreen.svg)](http://blog.csdn.net/gaolh89?viewmode=list)


致谢:
====

  如果您觉得我的此项目对您有些帮助,您的star就是对我最大的鼓励!


LICENSE
=======

    Copyright 2019 gaolhjy.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
