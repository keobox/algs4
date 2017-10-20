#/bin/sh

. java_home.env

ls target || mvn clean package
$JAVA_HOME/bin/java -cp target/algs4-1.0.0.0.jar org.keobox.algo.demo.DijkstraVsBFS algs4-data/largeEWD.txt 4
