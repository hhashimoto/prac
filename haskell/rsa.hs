-- encrypt n e m = m ^ e `mod` n
-- decrypt n d c = c ^ d `mod` n
xxcrypt n ed mc = mc ^ ed `mod` n
encrypt m = xxcrypt 138689 13 m
decrypt c = xxcrypt 138689 95497 c