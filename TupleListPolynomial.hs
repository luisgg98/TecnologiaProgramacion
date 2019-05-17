--Autores:
----Saúl Flores Benavente 755769
----Luis García Garcés 739202
module TupleListPolynomial where

    x :: [(Double,Double)]
    x = [(1,1)]
    
    --------------------------------------------------------------------------------------------------------
    
    coef :: Double -> [(Double,Double)]
    coef c = [(c,0)]
    
    --------------------------------------------------------------------------------------------------------

    add :: [(Double,Double)] -> [(Double,Double)] -> [(Double,Double)]
    add [] [] = []
    add x [] = x
    add [] x = x
    add (x:xs) (y:ys)
        |  (snd x) == (snd y) = ((fst x)+(fst y), snd x):( add xs ys )
        |  (snd x) > (snd y) = (fst x, snd x):( add xs (y:ys) )
        |  (snd x) < (snd y) = (fst y, snd y):( add (x:xs) ys )

    padd :: [[(Double,Double)]] -> [(Double,Double)]

    padd x = foldl (add) [] x

    --------------------------------------------------------------------------------------------------------

    mul :: [(Double,Double)] -> [(Double,Double)] -> [[(Double,Double)]] --Funcion auxilizar para la multiplicacion
    mul (x:[]) y = [(map ( \f -> ( (fst x) * (fst f), (snd x) + (snd f)) ) y)]
    mul x [] = [[]]
    mul (x:xs) y = (mul xs y)++[(map ( \f -> ( (fst x) * (fst f), (snd x) + (snd f)) ) y)]

    pmul :: [[(Double,Double)]] -> [(Double,Double)] --FUncion para multiplicar
    pmul m = foldr (\x y -> padd (mul (reverse x) y)) [(1,0)] m

    --------------------------------------------------------------------------------------------------------

    peval :: [(Double,Double)] -> Double -> Double
    peval x y = sum $ map (\q -> (fst q)*y**(snd q)) x

    --------------------------------------------------------------------------------------------------------

    pderv :: [(Double,Double)] -> [(Double,Double)]
    pderv p = map (\f -> ((fst f)*(snd f), (snd f - 1))) (init p)
