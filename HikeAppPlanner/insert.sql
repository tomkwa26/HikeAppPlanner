INSERT INTO `areas` (`name`, `description`) VALUES
('Pieniny', 'Malownicze pasmo górskie położone na granicy Polski i Słowacji'),
('Tatry', 'Najwyższe góry Polski i Słowacji. Są również najwyższym masywem górskim w łańcuchu Karpat'),
('Karkonosze', 'Najwyższy i największy masyw Sudetów leżący na granicy polsko-czeskiej'),
('Bieszczady', 'Przepiękne góry na wschodnich pograniczach Polski i Ukrainy');

INSERT INTO `categories` (`name`, `description`) VALUES
('Piesze górskie', 'Trasy w górach'),
('Piesze nizinne', 'Trasy na terenach nizinnych'),
('Widokowe', 'Szlaki zapewniające malownicze widoki na okolicę'),
('Rowerowe górskie', 'Wymagające trasy dla rowerzystów'),
('Rowerowe nizinne', 'Trasy na rodzinny wyjazd'),
('Dydaktyczne', 'Szlaki odpowiednie dla rodzin z dziećmi');

INSERT INTO `trails` (`name`, `color`, `length`, `description`, `category_id`, `area_id`,
                      `start_lat`, `start_lon`, `end_lat`, `end_lon`) VALUES
('Niedzica - Sromowce Wyżne - Macelak', 'czerwony', 8.3, 'Szlak łatwy, dla każdego', 3, 1, 49.421594, 20.317844, 49.418559, 20.379428),
('Bukowina - Pawlikowski Wierch - Łapsze – Niedzica', 'czerwony', 21.8, 'Wyjątkowy szlak o malowniczych widokach', 3, 1, 49.356999, 20.124988, 49.421594, 20.317844),
('Przełęcz Snozka - Macelak - Trzy Korony', 'niebieski', 10.1, 'Nazywany Głównym Szlakiem Pienińskim', 1, 1, 49.451151, 20.318969, 49.413823, 20.414188),
('Szczawnica - Szafranówka - Wysoka', 'niebieski', 9.5, 'Najpiękniejszy szlak w Pieninach', 1, 1, 49.425138, 20.482595, 49.380261, 20.555502),
('Trzy Korony - Sokolica - Szczawnica', 'niebieski', 9.0, 'Najpopularniejszy szlak w Pieninach', 1, 1, 49.413823, 20.414188, 49.425138, 20.482595),
('Nowa Biała - Trybsz - Pawlikowski Wierch', 'niebieski', 11.6, 'Piękne widoki na Beskidy, Pieniny i Tatry', 3, 1, 49.440770, 20.144021, 49.367277, 20.162037),
('Pawlikowski Wierch - Pieskowy Wierch - Kacwin', 'niebieski', 14.3, 'Piękne widoki na Beskidy, Pieniny i Tatry', 3, 1, 49.367277, 20.162037, 49.373888, 20.292889),
('Wysoka - Wierchliczka - Obidza', 'niebieski', 9.3, 'Wyjątkowo widowiskowy szlak', 3, 1, 49.380261, 20.555502, 49.420279, 20.616894),
('Jaworki - Wąwóz Homole - Wysoka', 'zielony', 4.0, 'Krótki szlak na najwyższy szczyt Pienin', 1, 1, 49.406615, 20.552783, 49.380261, 20.555502),
('Schronisko PTTK Orlica - Czerwony Klasztor', 'czerwony', 9.2, 'Nazywany Drogą Pienińską szlak wiodący wzdłuż Dunajca', 3, 1, 49.418789, 20.457604, 49.399017, 20.415790),
('Czerwony Klasztor - Wielki Lipnik', 'czerwony', 8.4, 'Atrakcyjna trasa Pienin słowackich', 1, 1, 49.399017, 20.415790, 49.374663, 20.499807),
('Krościenko nad Dunajcem - Przełęcz Szopka - Sromowce Niżne', 'żółty', 6.6, 'Popularna i atrakcyjna trasa', 1, 1, 49.440575, 20.427915, 49.395142, 20.409488);

INSERT INTO `areas_trail_categories` (`area_id`, `trail_category_id`) VALUES
(1, 1),
(1, 3),
(1, 4),
(1, 6),
(2, 1),
(2, 3),
(2, 4),
(2, 6);


