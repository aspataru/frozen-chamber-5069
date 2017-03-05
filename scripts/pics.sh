#! /bin/sh
# /etc/init.d/pics

### BEGIN INIT INFO
# Provides:          pics
# Required-Start:    $remote_fs $syslog
# Required-Stop:     $remote_fs $syslog
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: Starts the capture and upload daemon
# Description:       Starts the capture and upload daemon
### END INIT INFO

# If you want a command to always run, put it here
# sudo update-rc.d pics defaults
# sudo update-rc.d pics remove

# Carry out specific functions when asked to by the system
case "$1" in
  start)
    echo "Starting pics"
    # run application you want to start
    python /home/pi/software/parapi-server/scripts/pics.py &
    ;;
  stop)
    echo "Stopping pics"
    # kill application you want to stop
    kill $(ps aux | grep python | grep pics.py | awk '{print $2}')
    ;;
  *)
    echo "Usage: /etc/init.d/pics {start|stop}"
    exit 1
    ;;
esac

exit 0
