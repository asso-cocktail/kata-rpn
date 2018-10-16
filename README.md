La notation polonaise inverse (NPI) (en anglais RPN pour Reverse Polish Notation), également connue sous le nom de notation post-fixée, permet d'écrire de façon non ambiguë les formules arithmétiques sans utiliser de parenthèses.

Les calculatrices NPI sont fondées sur l’utilisation d’une pile, en d'autres termes les opérandes sont disposés au sommet de la pile, tandis que les résultats des calculs sont retournés aussi au sommet de la pile. Bien que ce concept puisse dérouter le débutant, la présentation d’une expression en notation polonaise inversée a l’avantage de la concision.

Cette technique a plusieurs avantages :
* l’ordre des opérandes est préservé ;
* les calculs se font en lisant l'expression de gauche à droite ;
* les opérandes précèdent l’opérateur et l'expression qui décrit chaque opérande disparaît lorsque l'opération est évaluée, pour être remplacée par la valeur calculée.

Exemples : 

* 5 3 + => 5+3 => 8
* 6 2 / => 6/2 => 3
* 5 2 - 7 + => 5 - 2 + 7 => 10
* 7 5 2 - + => 7 + (5 - 2) => 10
* 3 15 8 x 7 + x => 3x((15x8)+7) => 381
* 1 2 + 4 × 5 + 3 − => ((1+2) x 4) + 5 - 3 => 14
* 5 4 1 2 + × + => 5 + (4 x (1+2)) => 17
* 3 4 2 1 + x + 2 / => (3 + (4 x (2+1)))/2 => 7.5

