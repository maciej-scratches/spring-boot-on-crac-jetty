perf stat $DEMO_COMMAND &
export PERF_PID=$!
while [ "$(curl -s -o /dev/null -L -w ''%{http_code}'' http://localhost:8080/hello)" != "200" ];
  do sleep 0.001;
done
export MY_PID="$(pgrep -P $PERF_PID)"
kill $MY_PID
