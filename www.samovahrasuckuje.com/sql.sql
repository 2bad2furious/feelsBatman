-- nejbohatší šéf z každé fabriky
  -- rozděl si to

  SELECT pracuje.zam, pracuje.fab FROM pracuje
  JOIN(
  SELECT MAX(t1.plat) AS plat, pracuje.fab FROM (
  SELECT sefik.plat, sefik.fab FROM pracuje as sefik
  JOIN pracuje as zam ON zam.sef = sefik.zam) as t1
  GROUP BY t1.fab
  ) as t2 ON t2.plat = pracuje.plat AND t2.fab = pracuje.fab
  -- JOIN zamestanec ON zamestnanec.id = pracuje.zam

-- fabrika s nejvyšším průměrým platem manuálů

  SELECT MAX(t1.prumer) as prumer,t1.fab FROM(
  SELECT AVG(t2.plat) as prumer, t2.fab FROM (
  SELECT zam.plat, zam.fab FROM pracuje as sefik
  LEFT JOIN pracuje as zam ON zam.sef = sefik.zam WHERE sefik.id IS NULL) as t2
  ) as t1 GROUP BY t2.fab

  --SELECT t1.fab, MAX(t1.aveg) FROM fabrika JOIN(
  --SELECT AVG(manual.plat) as aveg FROM pracuje as sefik
  --JOIN pracuje as manual ON sefik.zam = manual.sef
  --WHERE sefik.zam IS NULL
  --GROUP BY pracuje.fab
  --) ON fabrika.id = t1.fab
  --GROUP BY t1.fab

-- Pro které fabriky pracuje člověk který pracuje pro nejvíc fabrik (GROUP_CONCAT)
  SELECT pracuje.zam, GROUP_CONCAT(DISTINCT fabrika.jmeno ORDER BY fabrika.jmeno SEPARATOR ', ') FROM pracuje JOIN(
  SELECT MAX(t1.c),t1.pracuje.zam FROM(
  SELECT pracuje.zam, COUNT(pracuje.zam) as c FROM pracuje
  GROUP BY pracuje.zam) as t1) AS t2 ON pracuje.zam = t2.zam
  JOIN fabrika ON fabrika.id = pracuje.fab

  --SELECT GROUP_CONCAT(DISTINCT fabrika.jmeno ORDER BY fabrika.jmeno SEPARATOR ', '), p.zam FROM fabrika
  --JOIN pracuje AS p ON p.fab = fabrika.id
  --JOIN (
  --SELECT MAX(t1.c), t1.zam FROM(
  --SELECT COUNT(pracuje.zam) as c,pracuje.zam FROM pracuje
  --JOIN zamestnanec as z ON z.id = pracuje.zam
  --GROUP BY pracuje.zam
  --) as t1 ) as t2 ON t2.zam = pracuje.zam
  --GROUP BY p.zam