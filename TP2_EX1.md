### Exercice 1 : Représentation des solutions et définition de la fonction objectif##

#### 1. Représentation des solutions (codage)##

a) Variables de décision (gènes) 
- Chaque bit du nombre codé sur \( n \) bits représente une variable de décision (un gène). 
- Les gènes sont donc les positions des bits dans le nombre binaire.

b) Valeurs possibles pour chaque gène
- Chaque gène peut prendre deux valeurs possibles :  
  - \( 0 \) : le bit est éteint.  
  - \( 1 \) : le bit est allumé.  
- Par conséquent :  
  - Valeur minimale : \( 0 \).  
  - Valeur maximale : \( 1 \).

c) Chromosome 
- Un chromosome est une séquence binaire de \( n \) bits, par exemple :  
  - \( 1011010 \), où chaque bit est un gène.  
- Il représente un nombre entier en base 2 compris entre \( 0 \) et \( 2^n - 1 \).

#### 2. Taille de l’espace de recherche##
- L’espace de recherche est constitué de tous les nombres possibles que l’on peut coder sur \( n \) bits.  
- La taille de cet espace est donc \( 2^n \), car chaque bit a deux états possibles (\( 0 \) ou \( 1 \)).

#### 3. Fonction objectif##
La fonction objectif doit évaluer la qualité d’un individu (chromosome) en fonction du problème posé, qui consiste à maximiser le nombre représenté par le chromosome.

- Fonction objectif :  
  Si un chromosome est représenté par \( C \), un nombre binaire de \( n \) bits, sa valeur décimale est :  
  \[f(C) = \text{valeur décimale de } C.\]
  En binaire, cela revient à :  
  \[f(C) = \sum_{i=0}^{n-1} c_i \cdot 2^i,\]
  où \( c_i \) est la valeur du bit à la position \( i \) (0 ou 1).

- Objectif : Maximiser \( f(C) \) pour atteindre la valeur maximale possible, soit \( 2^n - 1 \).

---

Résumé des réponses

Représentation des solutions :
   a) Variables de décision : bits d’un nombre binaire.  
   b) Valeurs possibles pour chaque gène : \( 0 \) ou \( 1 \).  
   c) Chromosome : une séquence binaire de \( n \) bits.

2. Taille de l’espace de recherche : 
   \( 2^n \).

   3. Fonction objectif :
   Maximiser la valeur décimale du chromosome, donnée par \( f(C) = \sum_{i=0}^{n-1} c_i \cdot 2^i \).  
   La solution optimale est le chromosome où tous les bits sont à \( 1 \), soit \( 111...1 \) en binaire (\( 2^n - 1 \) en décimal).
