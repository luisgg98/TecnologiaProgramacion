--Autores:
----Saúl Flores Benavente 755769
----Luis García Garcés 739202
module ArrayPolynomial where

x :: [Double]
x = [1,0]

--------------------------------------------------------------------------------------------------------

coef :: Double -> [Double]
coef c = [c]

--------------------------------------------------------------------------------------------------------

aux :: Int -> Int -> [Double] -> [Double] -> [Double]

aux l1 l2 (x:xs) (y:ys)
    | l1 == l2 = zipWith (+) (x:xs) (y:ys)
    | l1 > l2 = x : ( aux (l1-1) l2 xs (y:ys) )
    | l1 < l2 = y : ( aux l1 (l2-1) (x:xs) ys )

add :: [Double] -> [Double] -> [Double]
add [] [] = []
add x [] = x
add [] x = x
add x y = aux (length x) (length y) x y

padd :: [[Double]] -> [Double]

padd x = foldl (add) [] x

--------------------------------------------------------------------------------------------------------

mul :: [Double] -> [Double] -> [[Double]] --Funcion auxilizar para la multiplicacion
mul (x:[]) y = [(map ( x* ) y)]
mul x [] = [[]]
mul (x:xs) y = (mul xs (y++[0]))++[(map ( x* ) y)]

pmul :: [[Double]] -> [Double] --FUncion para multiplicar
pmul m = foldr (\x y -> padd (mul (reverse x) y)) [1] m

--------------------------------------------------------------------------------------------------------

peval :: [Double] -> Double -> Double
peval x y = sum $ zipWith (\q i -> q*y^i) (reverse x) [0..]

--------------------------------------------------------------------------------------------------------

pderv :: [Double] -> [Double]
pderv p = reverse $ tail $ zipWith (\x y -> x*y) (reverse p) [0..]
