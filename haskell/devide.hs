devide :: Double -> Double -> Double
devide 0 = id
devide a = (/ a)

devide2 :: Double -> Double -> Double
devide2 0 = const 0
devide2 a = (/ a)