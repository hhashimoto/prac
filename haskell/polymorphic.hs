import Data.Char(ord, toLower)

fromMaybe' _ (Just x) = x
fromMaybe' d Nothing = d

maybe' :: b -> (a -> b) -> Maybe a -> b
maybe' _ f (Just x) = f x
maybe' d _ Nothing = d

-- 7-4
fromMaybe2 :: a -> Maybe a -> a
fromMaybe2 d m = maybe d id m

const2 :: a -> b -> a
const2 x _ = x

-- 7-5
const' :: a -> b -> b
const' _ x = x

(.$.) :: (a -> b) -> a -> b
f .$. x = f x

-- 7-6
apply :: a -> (a -> b) -> b
apply x f = f x

(...) :: (b -> c) -> (a -> b) -> (a -> c)
(...) f g x = f $ g x

-- 7-7
toLowerOrd :: Char -> Int
toLowerOrd c = (ord . toLower) c

flip' :: (a -> b -> c) -> b -> a -> c
flip' f x y = f y x

-- 7-8
flip13 :: (a -> b -> c -> d) -> c -> b -> a -> d
flip13 f x y z = f z y x

on' :: (b -> b -> c) -> (a -> b) -> a -> a -> c
on' g f x y = g (f x) (f y)

-- 7-9
on3 :: (b -> b -> b -> c) -> (a -> b) -> a -> a -> a -> c
on3 g f x y z = g (f x) (f y) (f z)