--Autores:
----Saúl Flores Benavente 755769
----Luis García Garcés 739202
module BinaryTree where

data Tree a = EmptyTree | Branch a (Tree a) (Tree a) deriving Show

empty  :: Tree a
empty = EmptyTree

leaf :: a -> Tree a
leaf x = Branch x (empty) (empty)

tree :: a -> Tree a -> Tree a -> Tree a --Da problemas
tree  x lb rb = Branch x (lb) (rb)

size :: Tree a -> Float
size EmptyTree = 0
size (Branch x l d) = 1+size l + size d

preorder :: Tree a -> [a]
preorder EmptyTree = []
preorder (Branch x l d)= [x] ++ preorder l ++preorder d

postorder :: Tree a -> [a]
postorder EmptyTree = []
postorder (Branch x l d)= postorder l ++postorder d ++ [x]  

inorder :: Tree a -> [a]
inorder EmptyTree = []
inorder (Branch x l d)= inorder l ++ [x] ++ inorder d
 
add :: Ord a => Tree a -> a -> Tree a
add EmptyTree x = leaf x
add (Branch p l d) x 
    | x <= p = Branch p (add l x) d 
    | x > p = Branch p l (add d x)

between :: Ord a =>  Tree a -> a -> a -> [a]
between EmptyTree _ _  = []
between (Branch x l d) xmin xmax
    | x > xmax = between l xmin xmax
    | x < xmin = between d xmin xmax
    | xmin <= x && x <= xmax = [x] ++ (between l xmin xmax) ++ (between d xmin xmax)