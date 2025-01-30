JC = javac
JCFLAGS = -d build -sourcepath "src" -encoding UTF-8 -implicit:none #-Xlint:unchecked
JVM = java
JVMFLAGS = -ea -classpath ".:build"
JARNAME = bake.jar
ENTRY = fr.iutfbleau.izanicAissiGallego.projet.main.Main

package = build/fr/iutfbleau/izanicAissiGallego/projet
src = src/fr/iutfbleau/izanicAissiGallego/projet


$(package)/main/Main.class: $(src)/main/Main.java $(package)/cible/Cible.class $(package)/bakefile/BakefileParser.class $(package)/graphe/Noeud.class
	$(JC) $(JCFLAGS) $(src)/main/Main.java

$(package)/cible/Cible.class: $(src)/cible/Cible.java $(package)/processus/Processus.class $(package)/cible/Fichier.class
	$(JC) $(JCFLAGS) $(src)/cible/Cible.java
	

$(package)/processus/Processus.class: $(src)/processus/Processus.java
	$(JC) $(JCFLAGS) $(src)/processus/Processus.java

	
$(package)/bakefile/Variables.class: $(src)/bakefile/Variables.java
	$(JC) $(JCFLAGS) $(src)/bakefile/Variables.java
	
	
$(package)/cible/Fichier.class: $(src)/cible/Fichier.java
	$(JC) $(JCFLAGS) $(src)/cible/Fichier.java

$(package)/bakefile/BakefileParser.class: $(src)/bakefile/BakefileParser.java $(package)/bakefile/Variables.class $(package)/cible/Cible.class $(package)/graphe/Noeud.class
	$(JC) $(JCFLAGS) $(src)/bakefile/BakefileParser.java


	
$(package)/graphe/Noeud.class: $(src)/graphe/Noeud.java 
	$(JC) $(JCFLAGS) $(src)/graphe/Noeud.java


run: 
	$(JVM) $(JVMFLAGS) $(ENTRY)
	
clean:
	rm -rf build
	

	
jar:
	make
	jar cvfe $(JARNAME) $(ENTRY) -C build .
	
javadoc:
	make
	javadoc -d docs -sourcepath src -subpackages fr.iutfbleau.izanicAissiGallego.projet
