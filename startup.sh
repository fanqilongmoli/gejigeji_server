PIDFILE=pid
if [ -f "$PIDFILE" ] && kill -0 $(cat "$PIDFILE"); then
	echo "=========进程在运行。。。准备杀死"
	PID="$(cat "$PIDFILE")"
	kill -9 $PID
	rm -rf "$PIDFILE"
	echo "=========进程kill完成,准备启动==========="
fi
nohup java -jar -Xms200m -Xmx256m  target/gejigeji-0.0.1.jar >> logs/nohup.out 2>&1 & echo $! > $PIDFILE
echo "=========启动完成========="
