function FillNian() {
    var b = new Date();  //取当前时间
    var nian = parseInt(b.getFullYear());  //取当前年份

    var str = "";

    for (var i = nian - 5; i < nian + 6; i++)  //显示前后的5年
    {　　　　//判断年的当前选中，选中当前的年份
        if (i == nian) {
            str = str + "<option  selected='selected'  value='" + i + "' >" + i + "</option>";
        }
        else {
            str = str + "<option value='" + i + "'>" + i + "</option>";
        }
    }
    //给id名是nian的下拉菜单中加添加html，html就是上面写到的str
    document.getElementById("nian").innerHTML = str;
}

function FillYue()
{
    var b = new Date();  //取当前时间
    var yue = parseInt(b.getMonth()+1);  //取当前月份

    var str = "";
    for(var i=1;i<13;i++)  //循环；月份是从1开始，一年12个月，小于13
    {　　　　　　　　//判断当前月份的选中
        if( i==yue)
        {
            str = str+"<option  selected='selected' value='"+i+"' >"+i+"</option>";
        }
        else
        {
            str = str+"<option value='"+i+"'>"+i+"</option>";
        }
    }
    document.getElementById("yue").innerHTML = str;  //将str值写到id名是yue的下拉列表中
}

function FillTian()
{
    var b = new Date();
    var tian = parseInt(b.getDate());  //获取当前天数

    var yue = document.getElementById("yue").value;  //找到月的值
    var nian = document.getElementById("nian").value;  //找到年的值
    var ts = 31;

    //30号的月数：月数是4、6、9、11时，天数是30天
    if(yue==4 || yue==6 || yue==9 || yue==11)
    {
        ts=30;
    }

    //2月不同年的天
    if(yue==2)
    {　　　　//被4整除，同时不被100整除；或是被400整除的年
        if((nian%4==0 && nian%100 != 0) || nian%400==0)
        {
            ts = 29;  //闰年
        }
        else
        {
            ts = 28; //平年
        }
    }

    var str = "";
    for(var i=1;i<ts+1;i++)
    {　　　//判断天数是否选中
        if( i==tian)
        {
            str = str+"<option  selected='selected' value='"+i+"' >"+i+"</option>";
        }
        else
        {
            str = str+"<option value='"+i+"'>"+i+"</option>";
        }
    }
    document.getElementById("tian").innerHTML = str;  //将str的值给id名是天的下拉列表
}
