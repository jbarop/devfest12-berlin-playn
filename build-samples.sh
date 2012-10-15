rm -rf samples
cd samples-source
mvn package -Phtml
mv html/target/samples-html-1.0-SNAPSHOT/samples/ ../
cd ..

