-- Q 1 Které druhy mají průměrnou váhu přes 50?

SELECT druhy.id,AVG(zvire.vaha) AS prumer FROM druhy
JOIN zvire ON zvire.druh = druhy.id
GROUP BY druhy.id
HAVING prumer > 50

-- Q 2 Kdo je ošetřovatelem největšího počtu andulek? - blbě idečko musíš získat zvenku
  SELECT t1.id, MAX(t1.c) AS c FROM(
  SELECT osetrovatele.id, COUNT(osetrovatele.id) as c FROM osetrovatele
  JOIN osetruje ON osetruje.osetrovatel = osetrovatele.id
  JOIN zvirata ON zvirata.id = osetruje.zvire AND zvirata.druh = 1
  GROUP BY osetrovatele.id) as t1

-->

SELECT osetrovatele.id, COUNT(osetrovatele.id) as c FROM osetrovatele
  JOIN osetruje ON osetruje.osetrovatel = osetrovatele.id
  JOIN zvirata ON zvirata.id = osetruje.zvire
  JOIN druhy ON druhy.id = zvirata.druh AND druhy.nazev LIKE "andulka"
  GROUP BY osetrovatele.id
	ORDER BY c DESC
	LIMIT 1
-- Q 3 Vypište celkový počet "denních chodů" za předpokladu, že každý ošetřovatel nakrmí každého ze svých svěřenců 2x denně


-- R 1 Kteří ošetřovatelé nemají rádi vrabce?

SELECT osetrovalatele.id FROM osetrovatele
LEFT JOIN(
SELECT osetrovatele.id FROM osetrovatele
JOIN ma_rad ON ma_rad.osetrovatel = osetrovatele.id AND ma_rad.druh = 90
) as t1 ON t1.id = osetrovatele.id
WHERE t1.id IS NULL

-- R 2 Který ošetřovatel je nejvíce nenávistný? (Nemá rád nejvíce druhů) ? - blbě idečko musíš získat zvenku
SELECT osetrovatele.id, COUNT(ma_rad.druh) as c FROM osetrovatele
LEFT JOIN ma_rad ON ma_rad.osetrovatel = osetrovatele.id
GROUP BY osetrovatele.id
ORDER BY c
LIMIT 1

-- R 3 Kolik těžkých zvířat (váha přes 50) je krmeno méně než dvěma ošetřovateli?

SELECT COUNT(*) FROM(
SELECT COUNT(zvirata.id) as c, osetruje.zvire FROM osetrovatele
JOIN osetruje ON osetruje.osetrovatel = osetrovatele.id
JOIN zvirata ON zvirata.id = osetruje.zvire
GROUP BY osetruje.zvire
HAVING c < 2) as t1

-- S 1 Zvířata, která jsou krmena osobou, která je nemá ráda

SELECT DISTINCT zvirata.id,osetrovatele.id FROM osetrovatele
JOIN osetruje ON osetruje.osetrovatel = osetrovatele.id
JOIN zvirata ON zvirata.id = osetruje.zvire
LEFT JOIN ma_rad ON ma_rad.osetrovatel = osetrovatele.id AND zvirata.druh = ma_rad.druh
WHERE ma_rad.id IS NULL

-- S 2 Neošetřovaná, a přesto milovaná zvířata

SELECT DISTINCT zvirata.id FROM osetrovatele
JOIN osetruje ON osetruje.id = osetrovatele.id
RIGHT JOIN zvirata ON osetruje.zvire = zvirata.id
JOIN ma_rad ON zvirata.druh = ma_rad.druh
WHERE osetruje.id IS NULL

------ p 4

SELECT SUM(t1.pocet) FROM
  (SELECT zvirata.id, COUNT(ma_rad.id) + COUNT(osetruje.id) AS pocet
FROM zvirata
JOIN osetruje
ON osetruje.zvire = zvirata.id
LEFT JOIN ma_rad
ON ma_rad.druh = zvirata.druh
AND osetruje.osetrovatel = ma_rad.osetrovatel
GROUP BY zvirata.id) as t1

------------------

SELECT osetrovatele.id, COUNT(osetruje.id) AS count
FROM osetruje
JOIN Zvirata ON Zvirata.id = osetruje.zvire
LEFT JOIN ma_rad ON ma_rad.druh = Zvirata.druh AND ma_rad.osetrovatel = osetruje.osetrovatel
JOIN osetrovatele ON osetruje.osetrovatel = osetrovatele.id AND ma_rad.id IS NULL
GROUP BY osetrovatele.id
ORDER BY count DESC
LIMIT 1 -- xd

--- actutally correct

SELECT osetruje.osetrovatel, COUNT(osetruje.id) AS count
FROM osetruje
JOIN Zvirata ON Zvirata.id = osetruje.zvire
-- JOIN ma_rad ON ma_rad.druh = Zvirata.druh AND ma_rad.osetrovatel = osetruje.osetrovatel
LEFT JOIN(
SELECT DISTINCT osetruje.osetrovatel as id
FROM osetruje
JOIN Zvirata ON Zvirata.id = osetruje.zvire
JOIN ma_rad ON ma_rad.druh = Zvirata.druh AND ma_rad.osetrovatel = osetruje.osetrovatel
-- osetrovateleCoMajRadiNekohoKohoOsetrujou
) AS t1 ON t1.id = osetruje.osetrovatel
WHERE t1.id IS NULL
GROUP BY osetruje.osetrovatel
ORDER BY count DESC;
-- xd

-- k4 v2
SELECT Zvirata.*
FROM zvirata
JOIN druhy ON druhy.id = zvirata.druh
ORDER BY Zvirata.druh,Zvirata.narozen


