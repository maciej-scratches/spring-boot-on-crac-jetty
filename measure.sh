START=$(date +%s.%N)
while [ "$(curl -s -o /dev/null -L -w ''%{http_code}'' http://localhost:8080/hello)" != "200" ];
  do sleep 0.001;
done
END=$(date +%s.%N)
DIFF=$(echo "$END - $START" | bc)
echo "Measured"
echo DIFF
