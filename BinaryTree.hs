--Autores:
----Saúl Flores Benavente 755769
----Luis García Garcés 739202
module BinaryTree where

data Tree a = Null | Branch a (Tree a) (Tree a) 

instance Show a => Show (Tree a) where
    show (Branch x Null Null) = " ("++ show x ++") "
    show (Branch x i d) = " ("++ show i ++ show x ++ show d ++") "



empty  :: Tree a
empty = Null

leaf :: a -> Tree a
leaf x = Branch x (empty) (empty)

tree :: a -> Tree a -> Tree a -> Tree a --Da problemas
tree  x lb rb = Branch x (lb) (rb)

size :: Tree a -> Float
size Null = 0
size (Branch x l d) = 1+size l + size d

preorder :: Tree a -> [a]
preorder Null = []
preorder (Branch x l d)= [x] ++ preorder l ++preorder d

postorder :: Tree a -> [a]
postorder Null = []
postorder (Branch x l d)= postorder l ++postorder d ++ [x]  

inorder :: Tree a -> [a]
inorder Null = []
inorder (Branch x l d)= inorder l ++ [x] ++ inorder d
 
add :: Ord a => Tree a -> a -> Tree a
add Null x = leaf x
add (Branch p l d) x 
    | x <= p = Branch p (add l x) d 
    | x > p = Branch p l (add d x)

between :: Ord a =>  Tree a -> a -> a -> [a]
between Null _ _  = []
between (Branch x l d) xmin xmax
    | x > xmax = between l xmin xmax
    | x < xmin = between d xmin xmax
    | xmin <= x && x <= xmax = [x] ++ (between l xmin xmax) ++ (between d xmin xmax)