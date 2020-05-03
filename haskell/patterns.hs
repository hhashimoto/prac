maybe0 (Just x) = x
maybe0 Nothing = 0

nothingness Nothing = True
nothingness (Just _) = False

friend Nothing = "I'm alone."
friend (Just name) = name ++ " is my friend."

notZero 0 = Nothing
notZero x = Just x