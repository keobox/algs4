#/bin/sh

if [ $# -eq 2 ]; then
    JAR=algs4-1.0.0.0.jar
    ls target/$JAR || mvn clean package
    $JAVA_HOME/bin/java -cp target/$JAR org.keobox.algo.demo.DijkstraVsBFS algs4-data/largeEW$1.txt $2
else
    echo "$0 [D/G] s"
    echo "D=dense graph, G=sparse graph, s=source vertex"
fi
