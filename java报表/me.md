 

引入最新jq : 

```
<script src="http://code.jquery.com/jquery-latest.js"></script>
```

**目录**

[1\. 图片回显](about:blank#1.%20%E5%9B%BE%E7%89%87%E5%9B%9E%E6%98%BE)

[2\. 获取字符串长度及截取](about:blank#2.%20%E8%8E%B7%E5%8F%96%E5%AD%97%E7%AC%A6%E4%B8%B2%E9%95%BF%E5%BA%A6%E5%8F%8A%E6%88%AA%E5%8F%96)

[3\. 获取input标签的value值](about:blank#3.%20%E8%8E%B7%E5%8F%96input%E6%A0%87%E7%AD%BE%E7%9A%84value%E5%80%BC)

[4\. 表单提交校验(不能阻止表单提交,就算return false也会提交表单)](about:blank#4.%20%E8%A1%A8%E5%8D%95%E6%8F%90%E4%BA%A4%E6%A0%A1%E9%AA%8C%28%E4%B8%8D%E8%83%BD%E9%98%BB%E6%AD%A2%E8%A1%A8%E5%8D%95%E6%8F%90%E4%BA%A4%2C%E5%B0%B1%E7%AE%97return%20false%E4%B9%9F%E4%BC%9A%E6%8F%90%E4%BA%A4%E8%A1%A8%E5%8D%95%29)

[5\. 刷新当前页面](about:blank#5.%20%E5%88%B7%E6%96%B0%E5%BD%93%E5%89%8D%E9%A1%B5%E9%9D%A2)

[6\. 链接新标签也打开](about:blank#6.%20%E9%93%BE%E6%8E%A5%E6%96%B0%E6%A0%87%E7%AD%BE%E4%B9%9F%E6%89%93%E5%BC%80)

[7\. 小手样式](about:blank#7.%20%E5%B0%8F%E6%89%8B%E6%A0%B7%E5%BC%8F)

[8\. 图片无法正常显示时显示另一种图片](about:blank#8.%20%E5%9B%BE%E7%89%87%E6%97%A0%E6%B3%95%E6%AD%A3%E5%B8%B8%E6%98%BE%E7%A4%BA%E6%97%B6%E6%98%BE%E7%A4%BA%E5%8F%A6%E4%B8%80%E7%A7%8D%E5%9B%BE%E7%89%87)

[9\. 点击跳转](about:blank#9.%20%E7%82%B9%E5%87%BB%E8%B7%B3%E8%BD%AC)

[10\. 时间处理](about:blank#10.%20%E6%97%B6%E9%97%B4%E5%A4%84%E7%90%86)

[11\. 复选框](about:blank#11.%20%E5%A4%8D%E9%80%89%E6%A1%86)

[默认选中](about:blank#%E9%BB%98%E8%AE%A4%E9%80%89%E4%B8%AD)

[获取所有选中的复选框的value值](about:blank#%E8%8E%B7%E5%8F%96%E6%89%80%E6%9C%89%E9%80%89%E4%B8%AD%E7%9A%84%E5%A4%8D%E9%80%89%E6%A1%86%E7%9A%84value%E5%80%BC)

[12\. div](about:blank#12.%20div)

[替换div的内容](about:blank#%E6%9B%BF%E6%8D%A2div%E7%9A%84%E5%86%85%E5%AE%B9%C2%A0) 

[div追加内容](about:blank#div%E8%BF%BD%E5%8A%A0%E5%86%85%E5%AE%B9)

[13\. 删除确认](about:blank#13.%20%E5%88%A0%E9%99%A4%E7%A1%AE%E8%AE%A4)

[第一种方法：挺好用的，确认以后才能打开下载地址页面。原理也比较清晰。主要用于删除单条信息确认。](about:blank#%E7%AC%AC%E4%B8%80%E7%A7%8D%E6%96%B9%E6%B3%95%EF%BC%9A%E6%8C%BA%E5%A5%BD%E7%94%A8%E7%9A%84%EF%BC%8C%E7%A1%AE%E8%AE%A4%E4%BB%A5%E5%90%8E%E6%89%8D%E8%83%BD%E6%89%93%E5%BC%80%E4%B8%8B%E8%BD%BD%E5%9C%B0%E5%9D%80%E9%A1%B5%E9%9D%A2%E3%80%82%E5%8E%9F%E7%90%86%E4%B9%9F%E6%AF%94%E8%BE%83%E6%B8%85%E6%99%B0%E3%80%82%E4%B8%BB%E8%A6%81%E7%94%A8%E4%BA%8E%E5%88%A0%E9%99%A4%E5%8D%95%E6%9D%A1%E4%BF%A1%E6%81%AF%E7%A1%AE%E8%AE%A4%E3%80%82)

[第二种方法：原理跟上面的一样。](about:blank#%E7%AC%AC%E4%BA%8C%E7%A7%8D%E6%96%B9%E6%B3%95%EF%BC%9A%E5%8E%9F%E7%90%86%E8%B7%9F%E4%B8%8A%E9%9D%A2%E7%9A%84%E4%B8%80%E6%A0%B7%E3%80%82)

[第三种：主要用于批量删除的确认提示](about:blank#%E7%AC%AC%E4%B8%89%E7%A7%8D%EF%BC%9A%E4%B8%BB%E8%A6%81%E7%94%A8%E4%BA%8E%E6%89%B9%E9%87%8F%E5%88%A0%E9%99%A4%E7%9A%84%E7%A1%AE%E8%AE%A4%E6%8F%90%E7%A4%BA%C2%A0) 

[14\. 图片上传设置只能选择图片, 添加 accept="image/\*" 属性,点击选择文件时非图片不能选](about:blank#14.%20%E5%9B%BE%E7%89%87%E4%B8%8A%E4%BC%A0%E8%AE%BE%E7%BD%AE%E5%8F%AA%E8%83%BD%E9%80%89%E6%8B%A9%E5%9B%BE%E7%89%87%2C%20%E6%B7%BB%E5%8A%A0%C2%A0accept%3D%22image%2F*%22%20%E5%B1%9E%E6%80%A7%2C%E7%82%B9%E5%87%BB%E9%80%89%E6%8B%A9%E6%96%87%E4%BB%B6%E6%97%B6%E9%9D%9E%E5%9B%BE%E7%89%87%E4%B8%8D%E8%83%BD%E9%80%89)

[15\. js手机号码11位开头为1校验](about:blank#15.%20js%E6%89%8B%E6%9C%BA%E5%8F%B7%E7%A0%8111%E4%BD%8D%E5%BC%80%E5%A4%B4%E4%B8%BA1%E6%A0%A1%E9%AA%8C)

[16\. 使按钮不可用和可用](about:blank#16.%20%E4%BD%BF%E6%8C%89%E9%92%AE%E4%B8%8D%E5%8F%AF%E7%94%A8%E5%92%8C%E5%8F%AF%E7%94%A8)

[17\. 使隐藏及显示](about:blank#17.%20%E4%BD%BF%E9%9A%90%E8%97%8F%E5%8F%8A%E6%98%BE%E7%A4%BA)

[18\. 根据class获取按钮名称](about:blank#18.%20%E6%A0%B9%E6%8D%AEclass%E8%8E%B7%E5%8F%96%E6%8C%89%E9%92%AE%E5%90%8D%E7%A7%B0)

[19\. 限制显示行数,超出部分成省略号](about:blank#19.%20%E9%99%90%E5%88%B6%E6%98%BE%E7%A4%BA%E8%A1%8C%E6%95%B0%2C%E8%B6%85%E5%87%BA%E9%83%A8%E5%88%86%E6%88%90%E7%9C%81%E7%95%A5%E5%8F%B7)

[20\. ajax](about:blank#20.%20ajax)

[21\. select 下拉框](about:blank#21.%20select%20%E4%B8%8B%E6%8B%89%E6%A1%86)

[如何获得select被选中option的value和text](about:blank#%E5%A6%82%E4%BD%95%E8%8E%B7%E5%BE%97select%E8%A2%AB%E9%80%89%E4%B8%ADoption%E7%9A%84value%E5%92%8Ctext)

[设置默认选中](about:blank#%E8%AE%BE%E7%BD%AE%E9%BB%98%E8%AE%A4%E9%80%89%E4%B8%AD)

[22\. 定时事件](about:blank#22.%20%E5%AE%9A%E6%97%B6%E4%BA%8B%E4%BB%B6)

[执行一次](about:blank#%E6%89%A7%E8%A1%8C%E4%B8%80%E6%AC%A1)

[重复执行1](about:blank#%E9%87%8D%E5%A4%8D%E6%89%A7%E8%A1%8C1)

[重复执行2](about:blank#%E9%87%8D%E5%A4%8D%E6%89%A7%E8%A1%8C2)

[23\. onclick跳转](about:blank#23.%20onclick%E8%B7%B3%E8%BD%AC)

[24\. js提交表单](about:blank#24.%20js%E6%8F%90%E4%BA%A4%E8%A1%A8%E5%8D%95)

[25\. 倒计时](about:blank#25.%20%E5%80%92%E8%AE%A1%E6%97%B6)

[26\. 视频标签](about:blank#26.%20%E8%A7%86%E9%A2%91%E6%A0%87%E7%AD%BE)

[27\. 判断数组是否包含一个值](about:blank#27.%20%E5%88%A4%E6%96%AD%E6%95%B0%E7%BB%84%E6%98%AF%E5%90%A6%E5%8C%85%E5%90%AB%E4%B8%80%E4%B8%AA%E5%80%BC)

[28\. 获取n到m直接的随机数](about:blank#28.%20%E8%8E%B7%E5%8F%96n%E5%88%B0m%E7%9B%B4%E6%8E%A5%E7%9A%84%E9%9A%8F%E6%9C%BA%E6%95%B0)

[29\. 替换页面中的图片路径](about:blank#29.%20%E6%9B%BF%E6%8D%A2%E9%A1%B5%E9%9D%A2%E4%B8%AD%E7%9A%84%E5%9B%BE%E7%89%87%E8%B7%AF%E5%BE%84)

[30\. 解析json](about:blank#30.%20%E8%A7%A3%E6%9E%90json)

[31\. 遍历](about:blank#31.%20%E9%81%8D%E5%8E%86)

[32\. 时间格式化](about:blank#32.%20%E6%97%B6%E9%97%B4%E6%A0%BC%E5%BC%8F%E5%8C%96)

[33\. 切割字符串](about:blank#33.%20%E5%88%87%E5%89%B2%E5%AD%97%E7%AC%A6%E4%B8%B2)

[34\. 动态添加html绑定事件写法](about:blank#34.%20%E5%8A%A8%E6%80%81%E6%B7%BB%E5%8A%A0html%E7%BB%91%E5%AE%9A%E4%BA%8B%E4%BB%B6%E5%86%99%E6%B3%95)

[35\. 返回顶部](about:blank#35.%20%E8%BF%94%E5%9B%9E%E9%A1%B6%E9%83%A8)

[36\. 保留n位小数](about:blank#36.%20%E4%BF%9D%E7%95%99n%E4%BD%8D%E5%B0%8F%E6%95%B0)

[37\. 判断div是否存在](about:blank#37.%20%E5%88%A4%E6%96%ADdiv%E6%98%AF%E5%90%A6%E5%AD%98%E5%9C%A8)

[38\. 字符串](about:blank#38.%20%E5%AD%97%E7%AC%A6%E4%B8%B2)

[39\. 根据类名或者name获取所有的input或者textarea的value值,参考:js根据name获取所有的值\_TOP\_\_ONE的博客-CSDN博客\_js 根据name取值](about:blank#39.%20%E6%A0%B9%E6%8D%AE%E7%B1%BB%E5%90%8D%E6%88%96%E8%80%85name%E8%8E%B7%E5%8F%96%E6%89%80%E6%9C%89%E7%9A%84input%E6%88%96%E8%80%85textarea%E7%9A%84value%E5%80%BC%2C%E5%8F%82%E8%80%83%3Ajs%E6%A0%B9%E6%8D%AEname%E8%8E%B7%E5%8F%96%E6%89%80%E6%9C%89%E7%9A%84%E5%80%BC_TOP__ONE%E7%9A%84%E5%8D%9A%E5%AE%A2-CSDN%E5%8D%9A%E5%AE%A2_js%20%E6%A0%B9%E6%8D%AEname%E5%8F%96%E5%80%BC)

[40\. 判断input的type=file是否选择了图片](about:blank#40.%20%E5%88%A4%E6%96%ADinput%E7%9A%84type%3Dfile%E6%98%AF%E5%90%A6%E9%80%89%E6%8B%A9%E4%BA%86%E5%9B%BE%E7%89%87)

[41.点击弹出提示框](about:blank#41.%E7%82%B9%E5%87%BB%E5%BC%B9%E5%87%BA%E6%8F%90%E7%A4%BA%E6%A1%86)

[42.按钮点击跳转](about:blank#42.%E6%8C%89%E9%92%AE%E7%82%B9%E5%87%BB%E8%B7%B3%E8%BD%AC)

[43.失去焦点给另一个标签赋值](about:blank#43.%E5%A4%B1%E5%8E%BB%E7%84%A6%E7%82%B9%E7%BB%99%E5%8F%A6%E4%B8%80%E4%B8%AA%E6%A0%87%E7%AD%BE%E8%B5%8B%E5%80%BC)

[44.禁止输入与允许输入](about:blank#44.%E7%A6%81%E6%AD%A2%E8%BE%93%E5%85%A5%E4%B8%8E%E5%85%81%E8%AE%B8%E8%BE%93%E5%85%A5)

[45.获取按钮的文字](about:blank#45.%E8%8E%B7%E5%8F%96%E6%8C%89%E9%92%AE%E7%9A%84%E6%96%87%E5%AD%97)

[46.设置span的值](about:blank#46.%E8%AE%BE%E7%BD%AEspan%E7%9A%84%E5%80%BC)

[47.判定不等于undefined](about:blank#47.%E5%88%A4%E5%AE%9A%E4%B8%8D%E7%AD%89%E4%BA%8Eundefined)

[48.根据id添加删除class](about:blank#48.%E6%A0%B9%E6%8D%AEid%E6%B7%BB%E5%8A%A0%E5%88%A0%E9%99%A4class)

* * *

1\. 图片回显
--------

html :

```
<input type="file" name="files" accept="image/*" onchange="changImg(event)">
<img alt="再无图片" id="myImg" src="" height="100px" ,width="100px">
```

js :

```
function changImg(e) {
	for(var i = 0; i < e.target.files.length; i++) {
		var file = e.target.files.item(i);
                // 文件大小
                var len = e.target.files[i].size;
		if(!(/^image\/.*$/i.test(file.type))) {
			continue; //不是图片 就跳出这一次循环  
		}
		//实例化FileReader API  
		var freader = new FileReader();
		freader.readAsDataURL(file);
		freader.onload = function(e) {
			$("#myImg").attr("src", e.target.result);
		}
	}
}
```

  

清空file标签选择的文件,参考:[JavaScript将input file的选择的文件清空的两种解决方案\_曾燕辉的博客-CSDN博客](https://blog.csdn.net/yh_zeng2/article/details/75209026 "JavaScript将input file的选择的文件清空的两种解决方案_曾燕辉的博客-CSDN博客")

```
var obj = document.getElementById(id);
			obj.outerHTML = obj.outerHTML;
```

2\. 获取字符串长度及截取
--------------

```
var len = str.replace(/[\u0391-\uFFE5]/g, "aa").length;
var i1 = str.substring(0, 1);
```

3\. 获取input标签的value值
--------------------

```
var a=myform.name.value;
var a=document.getElementById("nn").value;
var contacts = $('input[name="modName"]').val();
```

注: myform是表单的id;

4\. 表单提交校验(不能阻止表单提交,就算return false也会提交表单)
-----------------------------------------

html:

```
<form class="apply-form" onsubmit="return check(this)"
```

js:

```
function check(form) {

			if (form.name.value == '') {
				alert("请输入用户帐号!");
				form.name.focus();
				return false;
			}
			if (form.phone.value == '') {
				alert("请输入登录密码!");
				form.phone.focus();
				return false;
			}
		
			return true;
		}
```

  


5\. 刷新当前页面
----------

```
location.replace(location.href);
```

参考 : [js刷新页面的几种方式与区别 - 郭磊—lily - 博客园](https://www.cnblogs.com/guoxianglei/p/9883164.html "js刷新页面的几种方式与区别 - 郭磊—lily - 博客园")

6\. 链接新标签也打开
------------

```
target="_blank"
```

7\. 小手样式
--------

```
style="cursor:pointer;"
```

  


8\. 图片无法正常显示时显示另一种图片
--------------------

```
onerror="javascript:this.src='${pageContext.request.contextPath}/assets/customer.png'"
```

  


9\. 点击跳转
--------

```
onclick="javascript:window.open('www.baidu.com');"
```
```
onclick="javascript:window.location.href='www.baidu.com';"
```

  


10\. 时间处理
---------

【1】获取毫秒值

var oldTime = (new Date("2012/12/25 20:11:11")).getTime(); //得到毫秒数  ==>  1356437471000

//不是上面格式的时间需要转换, 如下

   starttime ='2012-12-25 20:17:24';

    starttime = starttime.replace(new RegExp("-","gm"),"/");
    
    var starttimeHaoMiao = (new Date(starttime)).getTime(); //得到毫秒数 ==>  1356437844000

// 获取当前时间毫秒值

var currentTime = (new Date()).getTime();

【2】毫秒数转化为时间

var oldTime = (new Date("2012/12/25 20:11:11")).getTime(); //得到毫秒数  

var newTime = new Date(oldTime); //就得到普通的时间了 ==> Mon Feb 05 2018 10:53:06 GMT+0800 (中国标准时间)


【3】日期格式化

```
var ftTime = crtTimeFtt(1356437844000);  // ==> 2012-12-25 20:17:24
  	
  	//创建时间格式化显示
    function crtTimeFtt(value){
        var crtTime = new Date(value);
        return top.dateFtt("yyyy-MM-dd hh:mm:ss",crtTime);//转化成需要的格式     
    }
  	
	
    function dateFtt(fmt,date)   
    { //author: meizz   
      var o = {   
        "M+" : date.getMonth()+1,                 //月份   
        "d+" : date.getDate(),                    //日   
        "h+" : date.getHours(),                   //小时   
        "m+" : date.getMinutes(),                 //分   
        "s+" : date.getSeconds(),                 //秒   
        "q+" : Math.floor((date.getMonth()+3)/3), //季度   
        "S"  : date.getMilliseconds()             //毫秒   
      };   
      if(/(y+)/.test(fmt))   
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
      for(var k in o)   
        if(new RegExp("("+ k +")").test(fmt))   
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
      return fmt;   
    } 
```

【4】时间加减

参考 : [js 中处理时间相加减问题 - 菜小鱼~ - 博客园](https://www.cnblogs.com/whycai/p/12589402.html "js 中处理时间相加减问题 - 菜小鱼~ - 博客园")

```
// 字符串转时间
		var dataCur = new Date("2021-06-30 00:00:00");
		console.log(timeAddDay(dataCur, 2)) // 2021-07-02 00:00:00

		/**
		 * 时间加减天数
		 * @param {Date} date
		 * @param {int} dayCount
		 */
		function timeAddDay(date, dayCount) {
			dataCur = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + " 00:00:00";
			var dataCurPar = Date.parse(new Date(dataCur)) / 1000;
			dataCurPar += 86400 * dayCount;
			return getFullTime(new Date(parseInt(dataCurPar) * 1000));
		}

		/**
		 * 时间转日期
		 * @param {Date} date 
		 * @return {String} 2021-07-02 00:00:00
		 */
		function getFullTime(date) {
			Y = date.getFullYear() + '-',
				M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-',
				D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ',
				h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':',
				m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':',
				s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
			return Y + M + D + h + m + s
		}
```

11\. 复选框
--------

### 默认选中

```
<input name="checkbox" type="checkbox" value="checkbox" checked="checked" />
```

### 获取所有选中的复选框的value值

```
$("input[name='ck']:checkbox:checked").each(function() {
		console.log( $(this).val()) ;
	})
```

注: 获取所有name值为ck的input框的value值

12\. div
--------

### 替换div的内容 

```
var divA = document.getElementById("id");
```
```
divA.innerHTML = "要添加的内容";
```

### div追加内容

```
var divA = document.getElementById("id");
```
```
divA.innerHTML = divA.innerHTML+"要添加的内容";
```

或者

```
$("id").append("要添加的内容")
```

  


13\. 删除确认
---------

### 第一种方法：挺好用的，确认以后才能打开下载地址页面。原理也比较清晰。主要用于删除单条信息确认。

```
 
<SCRIPT LANGUAGE=javascript> 
function p_del() { 
var msg = "您真的确定要删除吗？\n\n请确认！"; 
if (confirm(msg)==true){ 
return true; 
}else{ 
return false; 
} 
} 
</SCRIPT> 
调用方法： 
<a href="del.jsp?id=<%=id%>" onclick="javascript:return p_del()">删 除</a> 

```

### 第二种方法：原理跟上面的一样。

```
<a href="javascript:if(confirm('确实要删除吗?'))location='jb51.php?id='">删除</a>
```

### 第三种：主要用于批量删除的确认提示 

```
<input name="Submit" type="submit" class="inputedit" value="删除" onclick="{if(confirm('确定纪录吗?')){this.document.formname.submit();return true;}return false;}"> 

<input name="按钮" type="button" ID="ok" onclick="{if(confirm('确定删除吗?')){window.location='Action.asp?Action=Del&TableName=Item&ID=<%=ID%>';return true;}return false;}" value="删除栏目" />

下面是别的网友整理的，大同小异。一般通过弹出确认按钮来判断是否继续进入下面的删除页面。
第一种: 
<a href="javascript:if(confirm('确认删除吗?'))window.location='del.asp'">删除</a> 
第二种: 
<script language="javascript"> 
<!-- 
function del_sure(){ 
var gnl=confirm("你真的确定要删除吗?"); 
if (gnl==true){ 
return true; 
} 
else{ 
return false; 
} 
} 
---> 
</script> 
//调用 
<a href="del.asp?id=<%=rs("id")%>" onclick="javascript:del_sure()">删除</a> 
第三种： 
<script language="javascript"> 
function confirmDel(str){ 
return confirm(str); 
} 
</script> 
<a href="delete.asp" onclick="return confirmDel('确定要删除吗')">删除</a>
```

14\. 图片上传设置只能选择图片, 添加 accept="image/\*" 属性,点击选择文件时非图片不能选
--------------------------------------------------------

```
<input class="b-file" type="file" name="file" id="fileUpload" accept="image/*" title="请选择头像">
```

15\. js手机号码11位开头为1校验
--------------------

```
var jmz = {};
		jmz.GetLength = function(str) {
			return str.replace(/[\u0391-\uFFE5]/g, "aa").length; //先把中文替换成两个字节的英文，在计算长度
		};
		var len = jmz.GetLength(myPhone);
		var i1 = myPhone.substring(0, 1);
		if(len == 11 && i1 == '1'){ 
			
		}else{
			return ;
		}
```

16\. 使按钮不可用和可用
--------------

不可用:

```
 $('.t-get').attr('disabled',"true");
```

可用:

```
$('.t-get').removeAttr("disabled");
```

17\. 使隐藏及显示
-----------

隐藏:

```
 $('.set-new-btn').attr('disabled',"true");
```

显示:

```
$('.set-new-btn').removeAttr("disabled"); 
```

或者

```
$(".company_img").show();
$(".company_img").hide();
```

18\. 根据class获取按钮名称
------------------

[http://bbs.csdn.net/topics/390452236](http://bbs.csdn.net/topics/390452236 "http://bbs.csdn.net/topics/390452236")

19\. 限制显示行数,超出部分成省略号
--------------------

```
word-break: break-all;


    text-overflow: ellipsis;


    display: -webkit-box; /** 对象作为伸缩盒子模型显示 **/


    -webkit-box-orient: vertical; /** 设置或检索伸缩盒对象的子元素的排列方式 **/


    -webkit-line-clamp: 3; /** 显示的行数 **/


    overflow: hidden;  /** 隐藏超出的内容 **/
```

```
style="word-break: break-all;text-overflow: ellipsis;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;"
```

  


20\. ajax
---------

```
	$.ajax({
		url : '/getImageBase',
		type : 'post',
		data : {
			"url" : url

		},
		success : function(data) {

		}
	});
```

上面的是异步请求,同步请求得添加属性 async: false, 如下 :

```
	$.ajax({
		url : '/getImageBase',
		type : 'post',
		async: false,
		data : {
			"url" : url

		},
		success : function(data) {

		}
	});
```

注: ajax分为同步和异步。一般使用异步，异步不会使页面阻塞，用户体验较好。但是异步会使编写js程序的复杂度提高(异步ajax中的代码和ajax外的代码执行顺序无法确定)，对新手来说很可能由于玩不转异步导致各种bug。

同步代码的缺点是 : 等待ajax请求响应之前，页面会卡住，用户什么都做不了，感受很不好，就像浏览器崩溃或者死机似的。所以同步ajax代码不适合应用到正式产品中。

21\. select 下拉框
---------------

### [如何获得select被选中option的value和text](http://www.cnblogs.com/zhaokuojiao/p/6055632.html "如何获得select被选中option的value和text")

一：[JavaScript](http://lib.csdn.net/base/javascript "JavaScript")原生的方法

1:拿到select对象： var myselect=document.getElementById(“test”);

2：拿到选中项的索引：var index=myselect.selectedIndex ; // selectedIndex代表的是你所选中项的index

3:拿到选中项options的value： myselect.options\[index\].value;

4:拿到选中项options的text： myselect.options\[index\].text;

二：[jQuery](http://lib.csdn.net/base/jquery "jQuery")方法（前提是已经加载了jquery库）

1:var options=$(“#test option:selected”); //获取选中的项

2:alert(options.val()); //拿到选中项的值

3:alert(options.text()); //拿到选中项的文本

### 设置默认选中

```
// 设置默认值  
        function f(s, v) {  
            m = $(s);  
            for(i = 0; i <= m.options.length; i++) {  
                if((m.options[i].value) == (v)) {  
                    m.options[i].selected = true;  
                    break;  
                }  
            }  
        } 
```

```
 // 获取对象  
        function $(id) {  
            return document.getElementById(id);  
        } 
```

s是select的id,v是下拉选项的value;

22\. 定时事件
---------

### 执行一次

```
//使用方法名字执行方法 setTimeout只执行一次
```

```
var t1 = window.setTimeout(hello,1000); 
var t2 = window.setTimeout("hello()",3000);//使用字符串执行方法 
window.clearTimeout(t1);//去掉定时器 


function hello(){ 
alert("hello"); 
} 
```

### 重复执行1

```
//重复执行某个方法 setInterval重复执行
var t1 = window.setInterval(hello,1000); 
var t2 = window.setInterval("hello()",3000); 
//去掉定时器的方法 
window.clearInterval(t1); 
```

### 重复执行2

```
setInterval(function() {
		hello();
	}, 1000);
```

23\. onclick跳转
--------------

　　①οnclick="javascript:window.location.href='[URL](http://www.cnblogs.com/JuneZhang/ "URL")'"

　　②οnclick="location='[URL](http://www.cnblogs.com/JuneZhang/ "URL")'"

　　③οnclick="window.location.href='[URL](http://www.cnblogs.com/JuneZhang/ "URL")?id=11'"

参考: [使用onclick跳转到其他页面/跳转到指定url - 冬雨在路上 - 博客园](https://www.cnblogs.com/JuneZhang/archive/2010/11/25/1887575.html "使用onclick跳转到其他页面/跳转到指定url - 冬雨在路上 - 博客园")

24\. js提交表单
-----------

```
document.getElementById("myForm").submit();
```

注: 不能有name或者id的值为"submit",否则会找不到函数;  


25\. 倒计时
--------

[JS倒计时两种种实现方式 - 黑仔002 - 博客园](https://www.cnblogs.com/heizai002/p/6862418.html "JS倒计时两种种实现方式 - 黑仔002 - 博客园")

26\. 视频标签
---------

```
<video  controls="controls" src="/avi/1.mp4" width="162px" height="122px"></video>
```

注: controls="controls" 是显示控件;

27\. 判断数组是否包含一个值
----------------

```
function isInArray(arr,value){
	    for(var i = 0; i < arr.length; i++){
	        if(value === arr[i]){
	            return true;
	        }
	    }
	    return false;
	}
```

注: 存在返回true,不存在返回false;

28\. 获取n到m直接的随机数
----------------

```
function randomNum(n, m) {
		switch (arguments.length) {
		case 1:
			return parseInt(Math.random() * minNum + 1, 10);
			break;
		case 2:
			return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10);
			break;
		default:
			return 0;
			break;
		}
	}
```

注: n和m有出现的几率;

29\. 替换页面中的图片路径
---------------

```
//一段正则，匹配所有_min.的图片src属性
	var test = /-min\./;
	//遍历所有的图片节点
	$("img").each(function(index, obj) {
		if (test.test($(this).attr("src"))) {
			var reSrc = $(this).attr("src").replace(test, ".");
			$(this).attr("src", reSrc)
		}
	})
```

说明: 获取所有img的src包含 "-min"的对象,然后把"-min"去掉;

30\. 解析json
-----------

[js读取解析JSON数据 - jtlgb - 博客园](https://www.cnblogs.com/jtlgb/p/6137915.html "js读取解析JSON数据 - jtlgb - 博客园")

31\. 遍历
-------

[http://www.jb51.net/article/45883.htm](http://www.jb51.net/article/45883.htm "http://www.jb51.net/article/45883.htm")

[js中的那些遍历 - 波先生 - 博客园](https://www.cnblogs.com/ihboy/p/6878427.html "js中的那些遍历 - 波先生 - 博客园")

32\. 时间格式化
----------

[js 中时间格式化的几种方法 - Mr\_伍先生 - 博客园](https://www.cnblogs.com/mr-wuxiansheng/p/6296646.html "js 中时间格式化的几种方法 - Mr_伍先生 - 博客园")

33\. 切割字符串
----------

[http://www.jb51.net/article/41507.htm](http://www.jb51.net/article/41507.htm "http://www.jb51.net/article/41507.htm")

34\. 动态添加html绑定事件写法
-------------------

$(document).on('click', '.informations\_b', function() {

})

这是动态添加html代码里的绑定click事件

35\. 返回顶部
---------

```
$('body,html').animate({
            "scrollTop": 0
        }, 500)
```

36\. 保留n位小数
-----------

```
Math.formatFloat = function (f,n) {
    // f: 需要处理的数
    // n: 保留小数位数
    var m = Math.pow(10, n);
    return Math.round(f * m, 10) / m;
}

```

37\. 判断div是否存在
--------------

```
	if($('.lookimg').length){
		console.log("类名为lookimg的标签存在!");
	}else{
		console.log("类名为lookimg的标签不存在!");
	}
```

38\. 字符串
--------

判断字符串是否为空或者全部都是空格,参考:[https://www.jb51.net/article/86543.htm](https://www.jb51.net/article/86543.htm "https://www.jb51.net/article/86543.htm")

```
function isNull(str){
			if ( str == "" ) return true;
			var regu = "^[ ]+$";
			var re = new RegExp(regu);
			return re.test(str);
		}
```

39\. 根据类名或者name获取所有的input或者textarea的value值,参考:[js根据name获取所有的值\_TOP\_\_ONE的博客-CSDN博客\_js 根据name取值](https://blog.csdn.net/top__one/article/details/64439806 "js根据name获取所有的值_TOP__ONE的博客-CSDN博客_js 根据name取值")
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

```
function te(){
		$("[name=a]").each(function () {
	        	alert($(this).val());
	        });
	}
```

```
function te(){
		$(".class").each(function () {
	        	alert($(this).val());
	        });
	}

```

40\. 判断input的type=file是否选择了图片
-----------------------------

```
$('#id').val().length
```

如果=0,说明没有选择文件

41.点击弹出提示框
----------

```
<a  href="http://www.baidu.com" onClick="return confirm('确定删除?');">[删除]</a>
```

参考 : [【web】a标签点击时跳出确认框\_michael\_ouyang的博客-CSDN博客\_a标签打开跳转弹出一个框](https://blog.csdn.net/michael_ouyang/article/details/52765575 "【web】a标签点击时跳出确认框_michael_ouyang的博客-CSDN博客_a标签打开跳转弹出一个框")

42.按钮点击跳转
---------

```
<input type="submit" name="Submit" value="同意" onclick=window.open(http://www.111cn.net/)>
```

注 : 上面的会打开新标签页,下面的不会

```
onclick="window.location='http://www.baidu.com'"
```

参考 : [js点击button按钮跳转到页面代码 - 山涧清泉 - 博客园](https://www.cnblogs.com/sjqq/p/8377775.html "js点击button按钮跳转到页面代码 - 山涧清泉 - 博客园")

43.失去焦点给另一个标签赋值
---------------

```
<input type="password" name="passw" id="pass" class="form-control">
<input type="hidden" name="password" id="pass2" class="form-control">

$("#pass").blur(function() {
	var password = hex_md5($('input[name="passw"]').val())
	var psel = document.getElementById("pass2");
	psel.value =password;
});
```

44.禁止输入与允许输入
------------

参考 : [HTML中如何使用JS将input输入框设置为禁止输入或者可输入\_kingsonzhang的博客-CSDN博客\_js 禁用input](https://blog.csdn.net/qq_18374629/article/details/90720589 "HTML中如何使用JS将input输入框设置为禁止输入或者可输入_kingsonzhang的博客-CSDN博客_js 禁用input")

45.获取按钮的文字
----------

参考 : [js获取按钮的文字\_longyinfengwu的博客-CSDN博客\_js获取button的文本](https://blog.csdn.net/tenggeer0789/article/details/91585517 "js获取按钮的文字_longyinfengwu的博客-CSDN博客_js获取button的文本")

46.设置span的值
-----------

```
$("#spId").text("testSpan");  
```

参考 : https://blog.csdn.net/xiaobing\_122613/article/details/78590157

47.判定不等于undefined
-----------------

[JS 中判断空值 undefined 和 null - Rainyn - 博客园](https://www.cnblogs.com/thiaoqueen/p/6904398.html "JS 中判断空值 undefined 和 null - Rainyn - 博客园")

```
var exp = undefined;
if (typeof(exp) == "undefined")
{
    alert("undefined");
}
```

48.根据id添加删除class
----------------

参考 : [原生js实现添加和删除class\_Ponnenult的博客-CSDN博客\_js删除class](https://blog.csdn.net/weixin_44727080/article/details/120460635 "原生js实现添加和删除class_Ponnenult的博客-CSDN博客_js删除class")

```
document.getElementById('show_one').style.display = 'block'
document.getElementById('show_two').style.display = 'none'
 
document.getElementById('click_one').classList.add("active")
document.getElementById('click_two').classList.remove("active")
```

49.随机数
------

[JavaScript如何创建随机整数-js教程-PHP中文网](https://m.php.cn/article/483202.html "JavaScript如何创建随机整数-js教程-PHP中文网")

```
// min最小值，max最大值
// 0,5  => 输出0到4之间的整数
function randomRange(min, max) { 
    return Math.floor(Math.random() * (max - min)) + min;
}
```

  

本文转自 [https://blog.csdn.net/Lxinccode/article/details/79081703](https://blog.csdn.net/Lxinccode/article/details/79081703)，如有侵权，请联系删除。