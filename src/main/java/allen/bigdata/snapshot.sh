
SYSTEM_PROPERTY=""

PID=$1
echo "tomcat pid="$1

if [ -e "/home/work/snapshot" ]; then
  echo "/home/work/snapshot exists"
  rm -f /home/work/snapshot/*.log
  rm -f /home/work/snapshot/*.hprof
else
  mkdir -p /home/work/snapshot;
fi

echo "开始存储当前机器负载情况"
cat /proc/loadavg > /home/work/snapshot/loadavg.log

echo "当前机器负载情况存储完成"


echo "开始抓堆栈共三次"
path="/home/work/snapshot/"$PID"_1_stack.log"
 $JAVA_HOME/bin/jstack $PID > $path
sleep 3
path="/home/work/snapshot/"$PID"_2_stack.log"
 $JAVA_HOME/bin/jstack $PID > $path
sleep 3
path="/home/work/snapshot/"$PID"_3_stack.log"
 $JAVA_HOME/bin/jstack $PID > $path
path="/home/work/snapshot/"$PID"_top10_thread.log"
top -bp $PID -H |tail -n +8 | head -n 10 > $path
 "堆栈抓取完成"

echo "记录当前堆的使用情况"
 $JAVA_HOME/bin/jmap -heap $PID > /home/work/snapshot/$PID"_heap.log"

echo "记录当前对象存活情况"
 $JAVA_HOME/bin/jmap -histo $PID > /home/work/snapshot/$PID"_histo.log"
 
echo "dump 当前进程的内存"
 $JAVA_HOME/bin/jmap -dump:format=b,file="/home/work/snapshot/"$PID".hprof" $PID

echo "记录当前进程GC情况，记录20S"
path="/home/work/snapshot/"$PID"_gc.log"
 $JAVA_HOME/bin/jstat -gcutil $PID 2000 10 > $path

echo "记录三个代的内存占用情况"
path="/home/work/snapshot/"$PID"_gccapacity.log"
 $JAVA_HOME/bin/jstat -gccapacity $PID > $path

# echo "记录连接当前tomcat的tcp连接数"
# path="/home/work/snapshot/"$PID"_tcp.log"
# netstat -n | grep "$TOMCATPORT" | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}' > $path

# echo "记录当前进程打开线程数"
# path="/home/work/snapshot/"$PID"_pstree.log"
# pstree $PID > $path

# echo "记录当前进程打开的文件数"
# path="/home/work/snapshot/"$PID"_lsof.log"
# lsof -p $PID | wc -l > $path

echo "系统当前快照完成，请手动转存log，并且重启服务,快照文件在 /home/work/snapshot目录下"