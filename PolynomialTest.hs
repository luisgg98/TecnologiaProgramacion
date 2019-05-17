import ArrayPolynomial
--import TupleListPolynomial

main = do
		print p
		print (peval p  2.0)
		print dp
		print (peval dp 2.0)
		where
			p  = padd [(pmul [x,x]),(coef 3.0),(pmul [(coef 2.0),x])]
			dp = pderv p

