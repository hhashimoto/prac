bmi :: Double -> Double -> Double
bmi height weight = weight / (height * 0.01) ^ 2

isObese :: Double -> Double -> Bool
isObese height weight = bmi height weight >= 25