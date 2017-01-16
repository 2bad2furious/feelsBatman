-- nejstarší zaměstnanec každý fabriky - chlap--

SELECT MIN(z.birthId),p.zam
FROM pracuje as p
JOIN zamestnanec as z
ON z.id = p.zam
WHERE SUBSTRING(z.birthId,3,1)<4  AND datumOd < NOW() AND datumDo > NOW()
GROUP BY p.fab

-- šéfová s nejvíce podřízených mladších mužů --

SELECT *
FROM



-- věkový průměr fabriky --