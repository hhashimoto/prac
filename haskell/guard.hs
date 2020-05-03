safeSqrt x
    | x >= 0 = Just (sqrt x)
    | otherwise = Nothing

div3 n
    | n `mod` 3 == 0 = n `div` 3
    | otherwise = n