double n = n * 2
mul23 m n = 2 ^ m * 3 ^ n

-- 5-6
nary n a b = a * n + b
octal a b = nary 8 a b

-- 5-7
tax price = floor $ price + price * 0.01 * 8
sec h m s = h * 3600 + m * 60 + s
tax' = \price -> floor $ price + price * 0.01 * 8
sec' = \h m s -> h * 3600 + m * 60 + s

-- 5-9
--price /*/ percent = floor $ price * (1 + 0.01 * percent)
price /*/ percent = (price * (100 + percent)) `div` 100

-- 5-10
nor b1 b2 = not $ b1 || b2
nor' b1 b2 = not b1 && not b2
isLarge n = n > 100