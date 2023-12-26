/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  041AlikinOS
 * Created: 28 нояб. 2023 г.
 */


INSERT INTO manufacturer (name)
VALUES ('Samsung'),
 ('OKI'),
 ('HP');

INSERT INTO model (name, print_color_type, print_format_type, print_speed, manufacturer_id)
VALUES ('ML-3051ND', 'BLACKANDWHITE', 1, 28, 
(SELECT id
FROM manufacturer
WHERE name = 'Samsung')),
('ML-3051', 'BLACKANDWHITE', 1, 28, 
(SELECT id
FROM manufacturer
WHERE name = 'Samsung')),
('NP2005g', 'BLACKANDWHITE', 1, 20, 
(SELECT id
FROM manufacturer
WHERE name = 'OKI')),
('NP2010g', 'BLACKANDWHITE', 1, 25, 
(SELECT id
FROM manufacturer
WHERE name = 'OKI')),
('KPM300', 'BLACKANDWHITE', 1, 23, 
(SELECT id
FROM manufacturer
WHERE name = 'HP')),
('KPM350', 'BLACKANDWHITE', 1, 24, 
(SELECT id
FROM manufacturer
WHERE name = 'HP'));


INSERT INTO cartridge_model (default_number_print_page, model, type)
VALUES 
(10000, 'ML-D3050A', 'ORIGINAL'),
(10000, 'ML-D3050B', 'ORIGINAL'),
(10000, 'BU555m', 'ORIGINAL'),
(10000, 'BU777n', 'ORIGINAL'),
(10000, 'OK9090', 'ORIGINAL'),
(10000, 'OK8080', 'ORIGINAL');

INSERT INTO cartridge_model_models_printers (model_cartridges_id, models_printers_id)
VALUES
((SELECT id
FROM cartridge_model WHERE model = 'ML-D3050A'),
(SELECT id 
FROM model WHERE name = 'ML-3051ND')),
((SELECT id
FROM cartridge_model WHERE model = 'ML-D3050B'),
(SELECT id 
FROM model WHERE name = 'ML-3051')),
((SELECT id
FROM cartridge_model WHERE model = 'BU555m'),
(SELECT id 
FROM model WHERE name = 'NP2005g')),
((SELECT id
FROM cartridge_model WHERE model = 'BU777n'),
(SELECT id 
FROM model WHERE name = 'NP2010g')),
((SELECT id
FROM cartridge_model WHERE model = 'OK9090'),
(SELECT id 
FROM model WHERE name = 'KPM300')),
((SELECT id
FROM cartridge_model WHERE model = 'OK8080'),
(SELECT id 
FROM model WHERE name = 'KPM350'));


INSERT INTO location (name)
VALUES
('Склад'),
('Белгород'),
('Губкин'),
('Короча');

INSERT INTO contract (contract_number, date_end_contract, date_start_contract)
VALUES
('43Белгород31', '2023-12-30', '2023-01-01'),
('24Губкин33', '2023-12-30', '2022-01-01'),
('64Короча11', '2023-12-30', '2023-01-01');

INSERT INTO object_buing (contract_id, location_id)
VALUES
((SELECT id
FROM contract WHERE contract_number = '43Белгород31'),
(SELECT id
FROM location WHERE name = 'Белгород')),
((SELECT id
FROM contract WHERE contract_number = '43Белгород31'),
(SELECT id
FROM location WHERE name = 'Белгород')),
((SELECT id
FROM contract WHERE contract_number = '24Губкин33'),
(SELECT id
FROM location WHERE name = 'Губкин')),
((SELECT id
FROM contract WHERE contract_number = '24Губкин33'),
(SELECT id
FROM location WHERE name = 'Губкин')),
((SELECT id
FROM contract WHERE contract_number = '64Короча11'),
(SELECT id
FROM location WHERE name = 'Короча')),
((SELECT id
FROM contract WHERE contract_number = '43Белгород31'),
(SELECT id
FROM location WHERE name = 'Белгород')),
((SELECT id
FROM contract WHERE contract_number = '43Белгород31'),
(SELECT id
FROM location WHERE name = 'Белгород')),
((SELECT id
FROM contract WHERE contract_number = '24Губкин33'),
(SELECT id
FROM location WHERE name = 'Губкин')),
((SELECT id
FROM contract WHERE contract_number = '24Губкин33'),
(SELECT id
FROM location WHERE name = 'Губкин')),
((SELECT id
FROM contract WHERE contract_number = '64Короча11'),
(SELECT id
FROM location WHERE name = 'Короча'));



INSERT INTO printer (inventory_number, printer_status, serial_number, printer_id, manufacturer_id, model_id)
VALUES 
('ВЦ3409180070', 'OK', '3N66BAJPA20756B', 1,
(SELECT id
FROM manufacturer WHERE name = 'Samsung'),
(SELECT id
FROM model WHERE name = 'ML-3051ND')),


('ог262723723462346', 'OK', '4572462643', 2,
(SELECT id
FROM manufacturer WHERE name = 'HP'),
(SELECT id
FROM model WHERE name = 'KPM300')),


('ок454к34533546', 'OK', '2343465463', 3,
(SELECT id
FROM manufacturer WHERE name = 'OKI'),
(SELECT id
FROM model WHERE name = 'NP2005g')),

('347234ма424644', 'OK', '2372342436', 4,
(SELECT id
FROM manufacturer WHERE name = 'Samsung'),
(SELECT id
FROM model WHERE name = 'ML-3051')),

('рке34342463464', 'OK', '3346344379', 5,
(SELECT id
FROM manufacturer WHERE name = 'OKI'),
(SELECT id
FROM model WHERE name = 'NP2010g'));


INSERT INTO cartridge (count, date_end_exploitation, date_start_exploitation, item_code, name_material, use_in_printer, util, cartridge_id, model_id, printer_id)
VALUES 
(NULL, NULL, NULL, '014055', 'Картридж оригинальный', false, false, 6,
(SELECT id
FROM cartridge_model WHERE model = 'ML-D3050A'), NULL),


(NULL, NULL, NULL, '6262456', 'Картридж оригинальный', false, false, 7,
(SELECT id
FROM cartridge_model WHERE model = 'ML-D3050B'), NULL),


(NULL, NULL, NULL, '3453374', 'Картридж оригинальный', false, false, 8,
(SELECT id
FROM cartridge_model WHERE model = 'BU555m'), NULL),

(NULL, NULL, NULL, '4684567', 'Картридж оригинальный', false, false, 9,
(SELECT id
FROM cartridge_model WHERE model = 'BU777n'), NULL),

(NULL, NULL, NULL, '01626834055', 'Картридж оригинальный', false, false, 10,
(SELECT id
FROM cartridge_model WHERE model = 'OK9090'), NULL);