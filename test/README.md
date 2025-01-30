le script refresh.sh permet de remettre les cas de test en situation

./refresh.sh pour executer


1 --> Compilation depuis rien. Aucun .class n'existe

2 -->  une compilation où le résultat existe déjà

3 --> une compilation où un résultat existe déjà, mais une source a été modifiée





4 --> une compilation où une dépendance circulaire est présente.

extrait du bakefile pour le test4:

${nom}.class: ${nom}.java ${nom}2.class
 
${nom}2.class:${nom}2.java ${nom}3.class ${nom}.class


${nom}.class dépend de ${nom}2.class qui lui meme depend de ${nom}.class


5 --> même exemple que le 4. mais avec un probleme de recette


6 --> Test compilation du projet en lui même
