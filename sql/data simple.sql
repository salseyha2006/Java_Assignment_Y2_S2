INSERT INTO departments (department_name) VALUES
('Human Resources'),
('Finance & Accounting'),
('Information Technology'),
('Marketing & Communications'),
('Sales & Business Development'),
('Customer Support'),
('Research & Development'),
('Legal & Compliance'),
('Operations & Logistics'),
('Product Management');

INSERT INTO employees (employee_code, first_name, last_name, gender, phone, email, position, hire_date, status, department_id) VALUES
-- ក្រុមទី 1 (ID 1 - 50)
('EMP001', 'Sok', 'Chan', 'Male', '012345678', 'sok.chan@company.com', 'HR Generalist', '2020-01-15', 'Active', 1),
('EMP002', 'Bopha', 'Seng', 'Female', '012345679', 'bopha.seng@company.com', 'Accountant', '2020-02-10', 'Active', 2),
('EMP003', 'Vireak', 'Keo', 'Male', '012345680', 'vireak.keo@company.com', 'Software Engineer', '2020-03-01', 'Active', 3),
('EMP004', 'Dara', 'Chea', 'Male', '012345681', 'dara.chea@company.com', 'Marketing Specialist', '2020-04-12', 'Active', 4),
('EMP005', 'Nary', 'Kim', 'Female', '012345682', 'nary.kim@company.com', 'Sales Executive', '2020-05-20', 'Active', 5),
('EMP006', 'Sopheap', 'Mao', 'Male', '012345683', 'sopheap.mao@company.com', 'Support Specialist', '2020-06-15', 'Active', 6),
('EMP007', 'Chantra', 'Ouk', 'Male', '012345684', 'chantra.ouk@company.com', 'R&D Engineer', '2020-07-01', 'Active', 7),
('EMP008', 'Sreyneang', 'Phon', 'Female', '012345685', 'sreyneang.phon@company.com', 'Legal Advisor', '2020-08-18', 'Active', 8),
('EMP009', 'Piseth', 'Heng', 'Male', '012345686', 'piseth.heng@company.com', 'Operations Coordinator', '2020-09-05', 'Active', 9),
('EMP010', 'Rith', 'Lim', 'Male', '012345687', 'rith.lim@company.com', 'Product Owner', '2020-10-10', 'Active', 10),
('EMP011', 'Sovan', 'Sok', 'Male', '012345688', 'sovan.sok@company.com', 'HR Manager', '2019-05-12', 'Active', 1),
('EMP012', 'Sreyroth', 'Pech', 'Female', '012345689', 'sreyroth.pech@company.com', 'Financial Analyst', '2020-11-01', 'Active', 2),
('EMP013', 'Phearin', 'Chon', 'Male', '012345690', 'phearin.chon@company.com', 'DevOps Engineer', '2021-01-20', 'Active', 3),
('EMP014', 'Channy', 'Thong', 'Female', '012345691', 'channy.thong@company.com', 'Content Writer', '2021-02-15', 'Active', 4),
('EMP015', 'Roth', 'Sam', 'Male', '012345692', 'roth.sam@company.com', 'Sales Representative', '2021-03-10', 'Active', 5),
('EMP016', 'Kunthea', 'Chhim', 'Female', '012345693', 'kunthea.chhim@company.com', 'Customer Service Lead', '2021-04-05', 'Active', 6),
('EMP017', 'Vannak', 'Long', 'Male', '012345694', 'vannak.long@company.com', 'Data Scientist', '2021-05-12', 'Active', 7),
('EMP018', 'Neary', 'Soun', 'Female', '012345695', 'neary.soun@company.com', 'Compliance Officer', '2021-06-01', 'Active', 8),
('EMP019', 'Rathana', 'Prak', 'Male', '012345696', 'rathana.prak@company.com', 'Logistics Manager', '2021-07-22', 'Active', 9),
('EMP020', 'Vibol', 'Nguon', 'Male', '012345697', 'vibol.nguon@company.com', 'Product Manager', '2021-08-14', 'Active', 10),
('EMP021', 'Leakhena', 'Sok', 'Female', '012345698', 'leakhena.sok@company.com', 'Recruiter', '2021-09-09', 'Active', 1),
('EMP022', 'Thida', 'Yim', 'Female', '012345699', 'thida.yim@company.com', 'Senior Accountant', '2018-04-15', 'Active', 2),
('EMP023', 'Maneth', 'Khim', 'Male', '012345700', 'maneth.khim@company.com', 'QA Engineer', '2021-10-05', 'Active', 3),
('EMP024', 'Serey', 'Vann', 'Male', '012345701', 'serey.vann@company.com', 'SEO Specialist', '2021-11-20', 'Active', 4),
('EMP025', 'Panha', 'Soun', 'Male', '012345702', 'panha.soun@company.com', 'Account Executive', '2021-12-15', 'Active', 5),
('EMP026', 'Somally', 'Tep', 'Female', '012345703', 'somally.tep@company.com', 'Support Agent', '2022-01-10', 'Active', 6),
('EMP027', 'Seyha', 'Meas', 'Male', '012345704', 'seyha.meas@company.com', 'Researcher', '2022-02-01', 'Active', 7),
('EMP028', 'Phalla', 'Lach', 'Female', '012345705', 'phalla.lach@company.com', 'Legal Assistant', '2022-03-05', 'Active', 8),
('EMP029', 'Sophal', 'Koeut', 'Male', '012345706', 'sophal.koeut@company.com', 'Warehouse Supervisor', '2022-04-18', 'Active', 9),
('EMP030', 'Kosal', 'Prom', 'Male', '012345707', 'kosal.prom@company.com', 'Associate Product Manager', '2022-05-22', 'Active', 10),
('EMP031', 'Sophorn', 'Tuy', 'Female', '012345708', 'sophorn.tuy@company.com', 'HR Assistant', '2022-06-30', 'Active', 1),
('EMP032', 'Visal', 'Nhem', 'Male', '012345709', 'visal.nhem@company.com', 'Junior Accountant', '2022-07-15', 'Active', 2),
('EMP033', 'Buntha', 'Ros', 'Male', '012345710', 'buntha.ros@company.com', 'Backend Developer', '2022-08-01', 'Active', 3),
('EMP034', 'Sreypich', 'Sao', 'Female', '012345711', 'sreypich.sao@company.com', 'Graphic Designer', '2022-09-10', 'Active', 4),
('EMP035', 'Vannith', 'Noun', 'Male', '012345712', 'vannith.noun@company.com', 'Sales Manager', '2019-10-01', 'Active', 5),
('EMP036', 'Sreyneang', 'Suong', 'Female', '012345713', 'sreyneang.suong@company.com', 'Customer Support Rep', '2022-10-05', 'Active', 6),
('EMP037', 'Makara', 'Cheon', 'Male', '012345714', 'makara.cheon@company.com', 'UI/UX Designer', '2022-11-12', 'Active', 3),
('EMP038', 'Kolap', 'Chhay', 'Female', '012345715', 'kolap.chhay@company.com', 'Legal Counsel', '2022-12-01', 'Active', 8),
('EMP039', 'Sarath', 'Prum', 'Male', '012345716', 'sarath.prum@company.com', 'Supply Chain Analyst', '2023-01-15', 'Active', 9),
('EMP040', 'Socheata', 'Ry', 'Female', '012345717', 'socheata.ry@company.com', 'Product Designer', '2023-02-20', 'Active', 10),
('EMP041', 'Odom', 'Hout', 'Male', '012345718', 'odom.hout@company.com', 'HR Specialist', '2023-03-01', 'Active', 1),
('EMP042', 'Sina', 'Seng', 'Female', '012345719', 'sina.seng@company.com', 'Payroll Coordinator', '2023-04-10', 'Active', 2),
('EMP043', 'Narin', 'Touch', 'Male', '012345720', 'narin.touch@company.com', 'Full Stack Developer', '2023-05-15', 'Active', 3),
('EMP044', 'Vibol', 'Choun', 'Male', '012345721', 'vibol.choun@company.com', 'Digital Marketer', '2023-06-01', 'Active', 4),
('EMP045', 'Malen', 'Em', 'Female', '012345722', 'malen.em@company.com', 'Key Account Manager', '2023-07-11', 'Active', 5),
('EMP046', 'Tola', 'Rin', 'Male', '012345723', 'tola.rin@company.com', 'Technical Support', '2023-08-20', 'Active', 6),
('EMP047', 'Ravuth', 'Yoeun', 'Male', '012345724', 'ravuth.yoeun@company.com', 'R&D Director', '2017-06-01', 'Active', 7),
('EMP048', 'Chanry', 'In', 'Female', '012345725', 'chanry.in@company.com', 'Compliance Manager', '2018-09-10', 'Active', 8),
('EMP049', 'Boramy', 'Moung', 'Female', '012345726', 'boramy.moung@company.com', 'Logistics Coordinator', '2023-09-15', 'Active', 9),
('EMP050', 'Sothea', 'Men', 'Male', '012345727', 'sothea.men@company.com', 'Senior Product Manager', '2019-02-15', 'Active', 10),

-- ក្រុមទី 2 (ID 51 - 100)
('EMP051', 'Vannet', 'Pech', 'Male', '012345728', 'vannet.pech@company.com', 'Recruiter', '2023-10-01', 'Active', 1),
('EMP052', 'Kanha', 'Oung', 'Female', '012345729', 'kanha.oung@company.com', 'Tax Specialist', '2023-11-05', 'Active', 2),
('EMP053', 'Phearom', 'Srun', 'Male', '012345730', 'phearom.srun@company.com', 'System Administrator', '2023-12-12', 'Active', 3),
('EMP054', 'Sophea', 'Ang', 'Female', '012345731', 'sophea.ang@company.com', 'PR Executive', '2024-01-10', 'Active', 4),
('EMP055', 'Narith', 'Chhoeun', 'Male', '012345732', 'narith.chhoeun@company.com', 'Sales Specialist', '2024-02-15', 'Active', 5),
('EMP056', 'Dalish', 'Koeut', 'Female', '012345733', 'dalish.koeut@company.com', 'Support Specialist', '2024-03-01', 'Active', 6),
('EMP057', 'Socheat', 'Phat', 'Male', '012345734', 'socheat.phat@company.com', 'Lead Researcher', '2020-05-18', 'Active', 7),
('EMP058', 'Thavry', 'Yim', 'Female', '012345735', 'thavry.yim@company.com', 'Legal Specialist', '2024-04-10', 'Active', 8),
('EMP059', 'Sokheng', 'Te', 'Male', '012345736', 'sokheng.te@company.com', 'Operations Specialist', '2024-05-20', 'Active', 9),
('EMP060', 'Kimhour', 'Tan', 'Male', '012345737', 'kimhour.tan@company.com', 'Product Analyst', '2024-06-15', 'Active', 10),
('EMP061', 'Seyma', 'Eang', 'Female', '012345738', 'seyma.eang@company.com', 'HR Coordinator', '2024-07-01', 'Active', 1),
('EMP062', 'Vuthy', 'Ly', 'Male', '012345739', 'vuthy.ly@company.com', 'Financial Controller', '2016-08-01', 'Active', 2),
('EMP063', 'Phanith', 'Phay', 'Male', '012345740', 'phanith.phay@company.com', 'Software Engineer II', '2022-06-01', 'Active', 3),
('EMP064', 'Phallin', 'Meak', 'Female', '012345741', 'phallin.meak@company.com', 'Event Organizer', '2024-08-12', 'Active', 4),
('EMP065', 'Moni', 'Soun', 'Male', '012345742', 'moni.soun@company.com', 'Commercial Manager', '2018-11-20', 'Active', 5),
('EMP066', 'Vichheka', 'Chun', 'Female', '012345743', 'vichheka.chun@company.com', 'Helpdesk Technician', '2024-09-01', 'Active', 6),
('EMP067', 'Kosal', 'Un', 'Male', '012345744', 'kosal.un@company.com', 'R&D Analyst', '2024-10-05', 'Active', 7),
('EMP068', 'Dany', 'Nong', 'Female', '012345745', 'dany.nong@company.com', 'Contracts Specialist', '2024-11-15', 'Active', 8),
('EMP069', 'Phanna', 'Khuon', 'Male', '012345746', 'phanna.khuon@company.com', 'Fleet Supervisor', '2024-12-01', 'Active', 9),
('EMP070', 'Channy', 'Phuong', 'Female', '012345747', 'channy.phuong@company.com', 'Associate Product Manager', '2025-01-10', 'Active', 10),
('EMP071', 'Theara', 'Sun', 'Male', '012345748', 'theara.sun@company.com', 'Compensation Specialist', '2025-02-15', 'Active', 1),
('EMP072', 'Chenda', 'Sorn', 'Female', '012345749', 'chenda.sorn@company.com', 'Accounting Clerk', '2025-03-01', 'Active', 2),
('EMP073', 'Samnang', 'Chay', 'Male', '012345750', 'samnang.chay@company.com', 'Frontend Developer', '2023-04-10', 'Active', 3),
('EMP074', 'Sreyleak', 'Mao', 'Female', '012345751', 'sreyleak.mao@company.com', 'Social Media Coordinator', '2025-04-12', 'Active', 4),
('EMP075', 'Rattanak', 'Prum', 'Male', '012345752', 'rattanak.prum@company.com', 'Sales Associate', '2025-05-20', 'Active', 5),
('EMP076', 'Sophorn', 'Uon', 'Female', '012345753', 'sophorn.uon@company.com', 'Support Specialist', '2025-06-15', 'Active', 6),
('EMP077', 'Dararith', 'Seng', 'Male', '012345754', 'dararith.seng@company.com', 'Mechanical Engineer', '2021-02-15', 'Active', 7),
('EMP078', 'Nika', 'Suon', 'Female', '012345755', 'nika.suon@company.com', 'Legal Assistant', '2025-07-01', 'Active', 8),
('EMP079', 'Sovanarith', 'Meas', 'Male', '012345756', 'sovanarith.meas@company.com', 'Inventory Specialist', '2025-08-10', 'Active', 9),
('EMP080', 'Pisey', 'Ros', 'Female', '012345757', 'pisey.ros@company.com', 'Junior Product Manager', '2025-09-01', 'Active', 10),
('EMP081', 'Sal', 'Mao', 'Male', '012345758', 'sal.mao@company.com', 'HR Director', '2015-01-10', 'Active', 1),
('EMP082', 'Sophanny', 'Khim', 'Female', '012345759', 'sophanny.khim@company.com', 'Audit Manager', '2017-03-15', 'Active', 2),
('EMP083', 'Visoth', 'Nao', 'Male', '012345760', 'visoth.nao@company.com', 'Database Administrator', '2020-04-01', 'Active', 3),
('EMP084', 'Leang', 'Lim', 'Male', '012345761', 'leang.lim@company.com', 'Creative Director', '2019-10-10', 'Active', 4),
('EMP085', 'Sreykhuoch', 'Srun', 'Female', '012345762', 'sreykhuoch.srun@company.com', 'Business Development Mgr', '2018-05-12', 'Active', 5),
('EMP086', 'Chanthan', 'Heng', 'Male', '012345763', 'chanthan.heng@company.com', 'Customer Experience Head', '2019-07-01', 'Active', 6),
('EMP087', 'Mengly', 'Hout', 'Male', '012345764', 'mengly.hout@company.com', 'Chemical Engineer', '2022-09-01', 'Active', 7),
('EMP088', 'Thary', 'Seng', 'Female', '012345765', 'thary.seng@company.com', 'Legal Counsel II', '2021-11-15', 'Active', 8),
('EMP089', 'Raksmey', 'Tep', 'Male', '012345766', 'raksmey.tep@company.com', 'Operations VP', '2015-05-01', 'Active', 9),
('EMP090', 'Vannary', 'Noun', 'Female', '012345767', 'vannary.noun@company.com', 'Director of Product', '2016-12-01', 'Active', 10),
('EMP091', 'Meng', 'Chea', 'Male', '012345768', 'meng.chea@company.com', 'HR Officer', '2025-10-01', 'Active', 1),
('EMP092', 'Monika', 'Lach', 'Female', '012345769', 'monika.lach@company.com', 'Junior Accountant', '2025-11-05', 'Active', 2),
('EMP093', 'Hak', 'Noun', 'Male', '012345770', 'hak.noun@company.com', 'Software Engineer I', '2025-12-10', 'Active', 3),
('EMP094', 'Leak', 'Sao', 'Female', '012345771', 'leak.sao@company.com', 'Marketing Assistant', '2026-01-15', 'Active', 4),
('EMP095', 'Chan', 'Tep', 'Male', '012345772', 'chan.tep@company.com', 'Sales Associate', '2026-02-10', 'Active', 5),
('EMP096', 'Thy', 'Ros', 'Male', '012345773', 'thy.ros@company.com', 'Customer Support Rep', '2026-03-01', 'Active', 6),
('EMP097', 'Sokly', 'Vann', 'Female', '012345774', 'sokly.vann@company.com', 'Lab Assistant', '2026-03-20', 'Active', 7),
('EMP098', 'Cheatra', 'Phon', 'Male', '012345775', 'cheatra.phon@company.com', 'Legal Clerk', '2026-04-10', 'Active', 8),
('EMP099', 'Kimleng', 'Un', 'Female', '012345776', 'kimleng.un@company.com', 'Logistics Analyst', '2026-05-01', 'Active', 9),
('EMP100', 'Veasna', 'Lim', 'Male', '012345777', 'veasna.lim@company.com', 'Product Coordinator', '2026-06-01', 'Active', 10),

-- ក្រុមទី 3 (ID 101 - 150)
('EMP101', 'Socheat', 'Khim', 'Male', '012345778', 'socheat.khim@company.com', 'HR Administrator', '2022-05-15', 'Active', 1),
('EMP102', 'Lida', 'Noun', 'Female', '012345779', 'lida.noun@company.com', 'Payroll Clerk', '2023-08-10', 'Active', 2),
('EMP103', 'Heng', 'Ros', 'Male', '012345780', 'heng.ros@company.com', 'Network Engineer', '2021-11-01', 'Active', 3),
('EMP104', 'Dany', 'Long', 'Female', '012345781', 'dany.long@company.com', 'SEO Analyst', '2023-03-12', 'Active', 4),
('EMP105', 'Reasmey', 'Sok', 'Male', '012345782', 'reasmey.sok@company.com', 'Sales Supervisor', '2020-09-20', 'Active', 5),
('EMP106', 'Vanna', 'Lach', 'Male', '012345783', 'vanna.lach@company.com', 'Technical Support Agent', '2024-01-15', 'Active', 6),
('EMP107', 'Chhavy', 'Noun', 'Female', '012345784', 'chhavy.noun@company.com', 'R&D Associate', '2023-12-01', 'Active', 7),
('EMP108', 'Sovann', 'Lim', 'Male', '012345785', 'sovann.lim@company.com', 'Senior Compliance Counsel', '2019-04-18', 'Active', 8),
('EMP109', 'Naran', 'Te', 'Male', '012345786', 'naran.te@company.com', 'Procurement Officer', '2022-07-05', 'Active', 9),
('EMP110', 'Borann', 'Srun', 'Male', '012345787', 'borann.srun@company.com', 'Product Specialist', '2023-10-10', 'Active', 10),
('EMP111', 'Sophorn', 'Phon', 'Female', '012345788', 'sophorn.phon@company.com', 'HR Generalist', '2024-02-28', 'Active', 1),
('EMP112', 'Muny', 'Chon', 'Male', '012345789', 'muny.chon@company.com', 'Tax Accountant', '2023-06-14', 'Active', 2),
('EMP113', 'Sopheak', 'Suon', 'Male', '012345790', 'sopheak.suon@company.com', 'Senior Developer', '2019-10-25', 'Active', 3),
('EMP114', 'Pich', 'Seng', 'Female', '012345791', 'pich.seng@company.com', 'Brand Manager', '2021-05-18', 'Active', 4),
('EMP115', 'Bunthoeun', 'Un', 'Male', '012345792', 'bunthoeun.un@company.com', 'Sales Manager', '2018-08-11', 'Active', 5),
('EMP116', 'Boran', 'Touch', 'Male', '012345793', 'boran.touch@company.com', 'Call Center Specialist', '2024-04-05', 'Active', 6),
('EMP117', 'Virak', 'Prum', 'Male', '012345794', 'virak.prum@company.com', 'Research Engineer', '2022-01-20', 'Active', 7),
('EMP118', 'Channy', 'Ouk', 'Female', '012345795', 'channy.ouk@company.com', 'Compliance Manager', '2021-09-08', 'Active', 8),
('EMP119', 'Makara', 'Khim', 'Male', '012345796', 'makara.khim@company.com', 'Warehouse Coordinator', '2023-11-30', 'Active', 9),
('EMP120', 'Visal', 'Ros', 'Male', '012345797', 'visal.ros@company.com', 'Product Lead', '2020-03-14', 'Active', 10),
('EMP121', 'Roth', 'Lim', 'Female', '012345798', 'roth.lim@company.com', 'Recruitment Specialist', '2024-07-15', 'Active', 1),
('EMP122', 'Ratha', 'Nhem', 'Male', '012345799', 'ratha.nhem@company.com', 'Senior Auditor', '2019-11-19', 'Active', 2),
('EMP123', 'Vann', 'Pech', 'Male', '012345800', 'vann.pech@company.com', 'Security Engineer', '2022-04-08', 'Active', 3),
('EMP124', 'Liza', 'Oung', 'Female', '012345801', 'liza.oung@company.com', 'Graphic Designer', '2024-09-22', 'Active', 4),
('EMP125', 'Sarath', 'Srun', 'Male', '012345802', 'sarath.srun@company.com', 'Enterprise Sales', '2020-06-17', 'Active', 5),
('EMP126', 'Kanha', 'Ang', 'Female', '012345803', 'kanha.ang@company.com', 'Customer Support Manager', '2020-12-05', 'Active', 6),
('EMP127', 'Borey', 'Chhoeun', 'Male', '012345804', 'borey.chhoeun@company.com', 'Lab Technician', '2023-02-12', 'Active', 7),
('EMP128', 'Dina', 'Koeut', 'Female', '012345805', 'dina.koeut@company.com', 'Legal Counsel', '2022-08-25', 'Active', 8),
('EMP129', 'Vuthy', 'Phat', 'Male', '012345806', 'vuthy.phat@company.com', 'Logistics Analyst', '2023-05-19', 'Active', 9),
('EMP130', 'Nika', 'Yim', 'Female', '012345807', 'nika.yim@company.com', 'Product Designer', '2023-11-11', 'Active', 10),
('EMP131', 'Thida', 'Te', 'Female', '012345808', 'thida.te@company.com', 'HR Intern', '2025-01-05', 'Active', 1),
('EMP132', 'Odom', 'Tan', 'Male', '012345809', 'odom.tan@company.com', 'Junior Accountant', '2024-12-01', 'Active', 2),
('EMP133', 'Maneth', 'Eang', 'Male', '012345810', 'maneth.eang@company.com', 'Software Engineer', '2024-10-15', 'Active', 3),
('EMP134', 'Sophal', 'Ly', 'Male', '012345811', 'sophal.ly@company.com', 'Public Relations Specialist', '2024-08-10', 'Active', 4),
('EMP135', 'Leakhena', 'Phay', 'Female', '012345812', 'leakhena.phay@company.com', 'Account Executive', '2024-06-05', 'Active', 5),
('EMP136', 'Seyha', 'Meak', 'Male', '012345813', 'seyha.meak@company.com', 'Customer Support Rep', '2025-03-01', 'Active', 6),
('EMP137', 'Sovan', 'Soun', 'Male', '012345814', 'sovan.soun@company.com', 'Senior Research Scientist', '2019-07-20', 'Active', 7),
('EMP138', 'Somally', 'Chun', 'Female', '012345815', 'somally.chun@company.com', 'Compliance Analyst', '2024-02-14', 'Active', 8),
('EMP139', 'Panha', 'Un', 'Male', '012345816', 'panha.un@company.com', 'Supply Chain Planner', '2023-09-01', 'Active', 9),
('EMP140', 'Serey', 'Nong', 'Male', '012345817', 'serey.nong@company.com', 'Product Owner', '2022-05-18', 'Active', 10),
('EMP141', 'Phalla', 'Khuon', 'Female', '012345818', 'phalla.khuon@company.com', 'Recruitment Coordinator', '2025-04-01', 'Active', 1),
('EMP142', 'Sophanny', 'Phuong', 'Female', '012345819', 'sophanny.phuong@company.com', 'Financial Analyst', '2024-05-10', 'Active', 2),
('EMP143', 'Sreyroth', 'Sun', 'Female', '012345820', 'sreyroth.sun@company.com', 'QA Engineer', '2025-02-01', 'Active', 3),
('EMP144', 'Narin', 'Sorn', 'Male', '012345821', 'narin.sorn@company.com', 'Digital Marketing Mgr', '2021-12-15', 'Active', 4),
('EMP145', 'Vannith', 'Chay', 'Male', '012345822', 'vannith.chay@company.com', 'Sales Supervisor', '2022-10-10', 'Active', 5),
('EMP146', 'Somally', 'Mao', 'Female', '012345823', 'somally.mao@company.com', 'Support Specialist', '2025-05-15', 'Active', 6),
('EMP147', 'Sreyneang', 'Suon', 'Female', '012345824', 'sreyneang.suon@company.com', 'R&D Engineer', '2024-11-20', 'Active', 7),
('EMP148', 'Makara', 'Meas', 'Male', '012345825', 'makara.meas@company.com', 'Contract Specialist', '2025-06-01', 'Active', 8),
('EMP149', 'Boramy', 'Ros', 'Female', '012345826', 'boramy.ros@company.com', 'Logistics Coordinator', '2025-03-12', 'Active', 9),
('EMP150', 'Sothea', 'Mao', 'Male', '012345827', 'sothea.mao@company.com', 'Associate Product Manager', '2024-12-10', 'Active', 10),

-- ក្រុមទី 4 (ID 151 - 200)
('EMP151', 'Vannet', 'Khim', 'Male', '012345828', 'vannet.khim@company.com', 'HR Officer', '2025-07-01', 'Active', 1),
('EMP152', 'Kanha', 'Nao', 'Female', '012345829', 'kanha.nao@company.com', 'Payroll Analyst', '2025-08-15', 'Active', 2),
('EMP153', 'Phearom', 'Lim', 'Male', '012345830', 'phearom.lim@company.com', 'System Administrator', '2024-03-20', 'Active', 3),
('EMP154', 'Sophea', 'Srun', 'Female', '012345831', 'sophea.srun@company.com', 'Communications Officer', '2025-09-10', 'Active', 4),
('EMP155', 'Narith', 'Heng', 'Male', '012345832', 'narith.heng@company.com', 'Inside Sales Rep', '2025-10-01', 'Active', 5),
('EMP156', 'Dalish', 'Hout', 'Female', '012345833', 'dalish.hout@company.com', 'Technical Support Agent', '2025-11-12', 'Active', 6),
('EMP157', 'Socheat', 'Seng', 'Male', '012345834', 'socheat.seng@company.com', 'R&D Analyst', '2025-05-18', 'Active', 7),
('EMP158', 'Thavry', 'Tep', 'Female', '012345835', 'thavry.tep@company.com', 'Legal Counsel', '2023-01-20', 'Active', 8),
('EMP159', 'Sokheng', 'Noun', 'Male', '012345836', 'sokheng.noun@company.com', 'Operations Coordinator', '2025-06-15', 'Active', 9),
('EMP160', 'Kimhour', 'Chea', 'Male', '012345837', 'kimhour.chea@company.com', 'Product Analyst', '2025-12-01', 'Active', 10),
('EMP161', 'Seyma', 'Lach', 'Female', '012345838', 'seyma.lach@company.com', 'HR Generalist', '2026-01-10', 'Active', 1),
('EMP162', 'Vuthy', 'Noun', 'Male', '012345839', 'vuthy.noun@company.com', 'Senior Accountant', '2022-02-15', 'Active', 2),
('EMP163', 'Phanith', 'Sao', 'Male', '012345840', 'phanith.sao@company.com', 'Frontend Developer', '2025-02-10', 'Active', 3),
('EMP164', 'Phallin', 'Tep', 'Female', '012345841', 'phallin.tep@company.com', 'Event Planner', '2025-03-25', 'Active', 4),
('EMP165', 'Moni', 'Ros', 'Male', '012345842', 'moni.ros@company.com', 'Account Manager', '2023-04-12', 'Active', 5),
('EMP166', 'Vichheka', 'Vann', 'Female', '012345843', 'vichheka.vann@company.com', 'Support Specialist', '2026-02-01', 'Active', 6),
('EMP167', 'Kosal', 'Phon', 'Male', '012345844', 'kosal.phon@company.com', 'Research Engineer', '2024-05-15', 'Active', 7),
('EMP168', 'Dany', 'Un', 'Female', '012345845', 'dany.un@company.com', 'Compliance Specialist', '2025-04-20', 'Active', 8),
('EMP169', 'Phanna', 'Lim', 'Male', '012345846', 'phanna.lim@company.com', 'Warehouse Supervisor', '2024-07-01', 'Active', 9),
('EMP170', 'Channy', 'Khim', 'Female', '012345847', 'channy.khim@company.com', 'Product Coordinator', '2025-10-15', 'Active', 10),
('EMP171', 'Theara', 'Noun', 'Male', '012345848', 'theara.noun@company.com', 'Benefits Specialist', '2025-11-01', 'Active', 1),
('EMP172', 'Chenda', 'Ros', 'Female', '012345849', 'chenda.ros@company.com', 'Tax Accountant', '2024-09-18', 'Active', 2),
('EMP173', 'Samnang', 'Long', 'Male', '012345850', 'samnang.long@company.com', 'Backend Developer', '2024-08-11', 'Active', 3),
('EMP174', 'Sreyleak', 'Sok', 'Female', '012345851', 'sreyleak.sok@company.com', 'PR Executive', '2025-12-05', 'Active', 4),
('EMP175', 'Rattanak', 'Lach', 'Male', '012345852', 'rattanak.lach@company.com', 'Sales Executive', '2024-06-22', 'Active', 5),
('EMP176', 'Sophorn', 'Noun', 'Female', '012345853', 'sophorn.noun@company.com', 'Customer Support Rep', '2026-03-10', 'Active', 6),
('EMP177', 'Dararith', 'Lim', 'Male', '012345854', 'dararith.lim@company.com', 'Mechanical Designer', '2023-11-14', 'Active', 7),
('EMP178', 'Nika', 'Te', 'Female', '012345855', 'nika.te@company.com', 'Legal Counsel', '2024-10-01', 'Active', 8),
('EMP179', 'Sovanarith', 'Srun', 'Male', '012345856', 'sovanarith.srun@company.com', 'Fleet Coordinator', '2025-01-22', 'Active', 9),
('EMP180', 'Pisey', 'Phon', 'Female', '012345857', 'pisey.phon@company.com', 'Product Specialist', '2025-02-18', 'Active', 10),
('EMP181', 'Sal', 'Chon', 'Male', '012345858', 'sal.chon@company.com', 'Recruiting Manager', '2022-08-01', 'Active', 1),
('EMP182', 'Sophanny', 'Suon', 'Female', '012345859', 'sophanny.suon@company.com', 'Internal Auditor', '2023-05-15', 'Active', 2),
('EMP183', 'Visoth', 'Seng', 'Male', '012345860', 'visoth.seng@company.com', 'Network Engineer', '2023-09-10', 'Active', 3),
('EMP184', 'Leang', 'Un', 'Male', '012345861', 'leang.un@company.com', 'Marketing Director', '2021-04-12', 'Active', 4),
('EMP185', 'Sreykhuoch', 'Touch', 'Female', '012345862', 'sreykhuoch.touch@company.com', 'Business Developer', '2024-04-05', 'Active', 5),
('EMP186', 'Chanthan', 'Prum', 'Male', '012345863', 'chanthan.prum@company.com', 'Customer Care Mgr', '2022-11-20', 'Active', 6),
('EMP187', 'Mengly', 'Ouk', 'Male', '012345864', 'mengly.ouk@company.com', 'Research Scientist', '2023-10-01', 'Active', 7),
('EMP188', 'Thary', 'Khim', 'Female', '012345865', 'thary.khim@company.com', 'Compliance Manager', '2022-07-15', 'Active', 8),
('EMP189', 'Raksmey', 'Ros', 'Male', '012345866', 'raksmey.ros@company.com', 'Logistics Director', '2021-01-10', 'Active', 9),
('EMP190', 'Vannary', 'Lim', 'Female', '012345867', 'vannary.lim@company.com', 'Product Manager', '2023-02-14', 'Active', 10),
('EMP191', 'Meng', 'Nhem', 'Male', '012345868', 'meng.nhem@company.com', 'HR Assistant', '2026-04-01', 'Active', 1),
('EMP192', 'Monika', 'Pech', 'Female', '012345869', 'monika.pech@company.com', 'Accounting Specialist', '2025-12-15', 'Active', 2),
('EMP193', 'Hak', 'Oung', 'Male', '012345870', 'hak.oung@company.com', 'Software Developer', '2025-07-10', 'Active', 3),
('EMP194', 'Leak', 'Srun', 'Female', '012345871', 'leak.srun@company.com', 'SEO Executive', '2026-05-10', 'Active', 4),
('EMP195', 'Chan', 'Ang', 'Male', '012345872', 'chan.ang@company.com', 'Sales Intern', '2026-06-15', 'Active', 5),
('EMP196', 'Thy', 'Chhoeun', 'Male', '012345873', 'thy.chhoeun@company.com', 'Support Technician', '2026-07-01', 'Active', 6),
('EMP197', 'Sokly', 'Koeut', 'Female', '012345874', 'sokly.koeut@company.com', 'Lab Analyst', '2026-04-12', 'Active', 7),
('EMP198', 'Cheatra', 'Phat', 'Male', '012345875', 'cheatra.phat@company.com', 'Legal Assistant', '2026-03-22', 'Active', 8),
('EMP199', 'Kimleng', 'Yim', 'Female', '012345876', 'kimleng.yim@company.com', 'Supply Chain Analyst', '2026-01-18', 'Active', 9),
('EMP200', 'Veasna', 'Te', 'Male', '012345877', 'veasna.te@company.com', 'Product Associate', '2025-09-01', 'Active', 10),

-- ក្រុមទី 5 (ID 201 - 250)
('EMP201', 'Socheat', 'Tan', 'Male', '012345878', 'socheat.tan@company.com', 'HR Specialist', '2024-04-15', 'Active', 1),
('EMP202', 'Lida', 'Eang', 'Female', '012345879', 'lida.eang@company.com', 'Finance Associate', '2024-11-20', 'Active', 2),
('EMP203', 'Heng', 'Ly', 'Male', '012345880', 'heng.ly@company.com', 'Systems Engineer', '2023-08-01', 'Active', 3),
('EMP204', 'Dany', 'Phay', 'Female', '012345881', 'dany.phay@company.com', 'Marketing Coordinator', '2024-12-10', 'Active', 4),
('EMP205', 'Reasmey', 'Meak', 'Male', '012345882', 'reasmey.meak@company.com', 'Sales Specialist', '2023-05-05', 'Active', 5),
('EMP206', 'Vanna', 'Soun', 'Male', '012345883', 'vanna.soun@company.com', 'Customer Care Advisor', '2025-02-15', 'Active', 6),
('EMP207', 'Chhavy', 'Chun', 'Female', '012345884', 'chhavy.chun@company.com', 'Senior Researcher', '2021-06-20', 'Active', 7),
('EMP208', 'Sovann', 'Un', 'Male', '012345885', 'sovann.un@company.com', 'Legal Specialist', '2023-10-10', 'Active', 8),
('EMP209', 'Naran', 'Nong', 'Male', '012345886', 'naran.nong@company.com', 'Procurement Specialist', '2024-03-01', 'Active', 9),
('EMP210', 'Borann', 'Khuon', 'Male', '012345887', 'borann.khuon@company.com', 'Product Specialist', '2024-09-15', 'Active', 10),
('EMP211', 'Sophorn', 'Phuong', 'Female', '012345888', 'sophorn.phuong@company.com', 'HR Officer', '2025-05-12', 'Active', 1),
('EMP212', 'Muny', 'Sun', 'Male', '012345889', 'muny.sun@company.com', 'Tax Advisor', '2024-07-18', 'Active', 2),
('EMP213', 'Sopheak', 'Sorn', 'Male', '012345890', 'sopheak.sorn@company.com', 'Frontend Engineer II', '2022-10-01', 'Active', 3),
('EMP214', 'Pich', 'Chay', 'Female', '012345891', 'pich.chay@company.com', 'Brand Specialist', '2023-11-20', 'Active', 4),
('EMP215', 'Bunthoeun', 'Mao', 'Male', '012345892', 'bunthoeun.mao@company.com', 'B2B Sales Mgr', '2020-12-15', 'Active', 5),
('EMP216', 'Boran', 'Uon', 'Male', '012345893', 'boran.uon@company.com', 'Technical Support Agent', '2025-08-01', 'Active', 6),
('EMP217', 'Virak', 'Seng', 'Male', '012345894', 'virak.seng@company.com', 'R&D Lead', '2022-03-10', 'Active', 7),
('EMP218', 'Channy', 'Suon', 'Female', '012345895', 'channy.suon@company.com', 'Compliance Auditor', '2023-04-12', 'Active', 8),
('EMP219', 'Makara', 'Meas', 'Male', '012345896', 'makara.meas@company.com', 'Inventory Coordinator', '2024-06-15', 'Active', 9),
('EMP220', 'Visal', 'Ros', 'Male', '012345897', 'visal.ros@company.com', 'Product Manager', '2022-01-20', 'Active', 10),
('EMP221', 'Roth', 'Mao', 'Female', '012345898', 'roth.mao@company.com', 'Recruiter', '2025-10-05', 'Active', 1),
('EMP222', 'Ratha', 'Khim', 'Male', '012345899', 'ratha.khim@company.com', 'Financial Analyst', '2023-09-01', 'Active', 2),
('EMP223', 'Vann', 'Nao', 'Male', '012345900', 'vann.nao@company.com', 'Infrastructure Engineer', '2023-02-15', 'Active', 3),
('EMP224', 'Liza', 'Lim', 'Female', '012345901', 'liza.lim@company.com', 'Graphic Designer', '2025-11-12', 'Active', 4),
('EMP225', 'Sarath', 'Srun', 'Male', '012345902', 'sarath.srun@company.com', 'Sales Specialist', '2022-05-18', 'Active', 5),
('EMP226', 'Kanha', 'Heng', 'Female', '012345903', 'kanha.heng@company.com', 'Support Lead', '2021-08-22', 'Active', 6),
('EMP227', 'Borey', 'Hout', 'Male', '012345904', 'borey.hout@company.com', 'Lab Assistant', '2024-12-01', 'Active', 7),
('EMP228', 'Dina', 'Seng', 'Female', '012345905', 'dina.seng@company.com', 'Legal Executive', '2023-07-10', 'Active', 8),
('EMP229', 'Vuthy', 'Tep', 'Male', '012345906', 'vuthy.tep@company.com', 'Logistics Specialist', '2024-05-15', 'Active', 9),
('EMP230', 'Nika', 'Noun', 'Female', '012345907', 'nika.noun@company.com', 'Product Designer', '2024-01-10', 'Active', 10),
('EMP231', 'Thida', 'Chea', 'Female', '012345908', 'thida.chea@company.com', 'HR Intern', '2026-03-01', 'Active', 1),
('EMP232', 'Odom', 'Lach', 'Male', '012345909', 'odom.lach@company.com', 'Junior Auditor', '2025-10-20', 'Active', 2),
('EMP233', 'Maneth', 'Noun', 'Male', '012345910', 'maneth.noun@company.com', 'QA Tester', '2025-06-15', 'Active', 3),
('EMP234', 'Sophal', 'Sao', 'Male', '012345911', 'sophal.sao@company.com', 'SEO Analyst', '2025-12-01', 'Active', 4),
('EMP235', 'Leakhena', 'Tep', 'Female', '012345912', 'leakhena.tep@company.com', 'Sales Representative', '2025-04-10', 'Active', 5),
('EMP236', 'Seyha', 'Ros', 'Male', '012345913', 'seyha.ros@company.com', 'Support Agent', '2026-05-15', 'Active', 6),
('EMP237', 'Sovan', 'Vann', 'Male', '012345914', 'sovan.vann@company.com', 'Senior Research Lead', '2020-09-01', 'Active', 7),
('EMP238', 'Somally', 'Phon', 'Female', '012345915', 'somally.phon@company.com', 'Compliance Officer', '2024-11-12', 'Active', 8),
('EMP239', 'Panha', 'Un', 'Male', '012345916', 'panha.un@company.com', 'Fleet Analyst', '2024-08-20', 'Active', 9),
('EMP240', 'Serey', 'Lim', 'Male', '012345917', 'serey.lim@company.com', 'Product Coordinator', '2024-02-14', 'Active', 10),
('EMP241', 'Phalla', 'Khim', 'Female', '012345918', 'phalla.khim@company.com', 'Recruiting Assistant', '2026-01-10', 'Active', 1),
('EMP242', 'Sophanny', 'Noun', 'Female', '012345919', 'sophanny.noun@company.com', 'Accountant', '2025-05-01', 'Active', 2),
('EMP243', 'Sreyroth', 'Ros', 'Female', '012345920', 'sreyroth.ros@company.com', 'Software Engineer', '2025-11-20', 'Active', 3),
('EMP244', 'Narin', 'Long', 'Male', '012345921', 'narin.long@company.com', 'Marketing Coordinator', '2024-07-05', 'Active', 4),
('EMP245', 'Vannith', 'Sok', 'Male', '012345922', 'vannith.sok@company.com', 'Sales Representative', '2024-09-12', 'Active', 5),
('EMP246', 'Somally', 'Lach', 'Female', '012345923', 'somally.lach@company.com', 'Customer Support Rep', '2026-06-01', 'Active', 6),
('EMP247', 'Sreyneang', 'Noun', 'Female', '012345924', 'sreyneang.noun@company.com', 'R&D Engineer', '2025-08-15', 'Active', 7),
('EMP248', 'Makara', 'Sok', 'Male', '012345925', 'makara.sok@company.com', 'Legal Specialist', '2025-12-10', 'Active', 8),
('EMP249', 'Boramy', 'Noun', 'Female', '012345926', 'boramy.noun@company.com', 'Supply Chain Planner', '2025-03-20', 'Active', 9),
('EMP250', 'Sothea', 'Srun', 'Male', '012345927', 'sothea.srun@company.com', 'Product Specialist', '2024-10-01', 'Active', 10),

-- ក្រុមទី 6 (ID 251 - 300)
('EMP251', 'Socheat', 'Suon', 'Male', '012345928', 'socheat.suon@company.com', 'HR Officer', '2025-11-15', 'Active', 1),
('EMP252', 'Lida', 'Meas', 'Female', '012345929', 'lida.meas@company.com', 'Billing Coordinator', '2025-06-01', 'Active', 2),
('EMP253', 'Heng', 'Ros', 'Male', '012345930', 'heng.ros@company.com', 'DevOps Analyst', '2025-01-20', 'Active', 3),
('EMP254', 'Dany', 'Mao', 'Female', '012345931', 'dany.mao@company.com', 'Content Writer', '2025-09-22', 'Active', 4),
('EMP255', 'Reasmey', 'Khim', 'Male', '012345932', 'reasmey.khim@company.com', 'Sales Associate', '2024-12-18', 'Active', 5),
('EMP256', 'Vanna', 'Nao', 'Male', '012345933', 'vanna.nao@company.com', 'Support Technician', '2026-04-05', 'Active', 6),
('EMP257', 'Chhavy', 'Lim', 'Female', '012345934', 'chhavy.lim@company.com', 'R&D Associate', '2024-03-12', 'Active', 7),
('EMP258', 'Sovann', 'Srun', 'Male', '012345935', 'sovann.srun@company.com', 'Legal Clerk', '2025-02-10', 'Active', 8),
('EMP259', 'Naran', 'Heng', 'Male', '012345936', 'naran.heng@company.com', 'Warehouse Coordinator', '2025-07-15', 'Active', 9),
('EMP260', 'Borann', 'Hout', 'Male', '012345937', 'borann.hout@company.com', 'Product Analyst', '2025-08-01', 'Active', 10),
('EMP261', 'Sophorn', 'Seng', 'Female', '012345938', 'sophorn.seng@company.com', 'HR Assistant', '2026-02-15', 'Active', 1),
('EMP262', 'Muny', 'Tep', 'Male', '012345939', 'muny.tep@company.com', 'Financial Specialist', '2025-04-10', 'Active', 2),
('EMP263', 'Sopheak', 'Noun', 'Male', '012345940', 'sopheak.noun@company.com', 'Software Engineer', '2025-05-18', 'Active', 3),
('EMP264', 'Pich', 'Chea', 'Female', '012345941', 'pich.chea@company.com', 'SEO Coordinator', '2026-03-12', 'Active', 4),
('EMP265', 'Bunthoeun', 'Lach', 'Male', '012345942', 'bunthoeun.lach@company.com', 'Inside Sales Specialist', '2024-11-20', 'Active', 5),
('EMP266', 'Boran', 'Noun', 'Male', '012345943', 'boran.noun@company.com', 'Customer Support Rep', '2026-06-10', 'Active', 6),
('EMP267', 'Virak', 'Sao', 'Male', '012345944', 'virak.sao@company.com', 'Material Scientist', '2023-09-01', 'Active', 7),
('EMP268', 'Channy', 'Tep', 'Female', '012345945', 'channy.tep@company.com', 'Contracts Administrator', '2024-05-15', 'Active', 8),
('EMP269', 'Makara', 'Ros', 'Male', '012345946', 'makara.ros@company.com', 'Logistics Associate', '2025-10-12', 'Active', 9),
('EMP270', 'Visal', 'Vann', 'Male', '012345947', 'visal.vann@company.com', 'Product Associate', '2025-01-15', 'Active', 10),
('EMP271', 'Roth', 'Phon', 'Female', '012345948', 'roth.phon@company.com', 'HR Generalist', '2026-01-20', 'Active', 1),
('EMP272', 'Ratha', 'Un', 'Male', '012345949', 'ratha.un@company.com', 'Accountant', '2024-12-05', 'Active', 2),
('EMP273', 'Vann', 'Lim', 'Male', '012345950', 'vann.lim@company.com', 'Web Developer', '2025-03-10', 'Active', 3),
('EMP274', 'Liza', 'Khim', 'Female', '012345951', 'liza.khim@company.com', 'Social Media Manager', '2024-10-14', 'Active', 4),
('EMP275', 'Sarath', 'Noun', 'Male', '012345952', 'sarath.noun@company.com', 'Key Account Executive', '2024-02-18', 'Active', 5),
('EMP276', 'Kanha', 'Ros', 'Female', '012345953', 'kanha.ros@company.com', 'Customer Support Agent', '2026-07-01', 'Active', 6),
('EMP277', 'Borey', 'Long', 'Male', '012345954', 'borey.long@company.com', 'R&D Lab Assistant', '2024-06-20', 'Active', 7),
('EMP278', 'Dina', 'Sok', 'Female', '012345955', 'dina.sok@company.com', 'Legal Counsel', '2024-09-01', 'Active', 8),
('EMP279', 'Vuthy', 'Lach', 'Male', '012345956', 'vuthy.lach@company.com', 'Procurement Analyst', '2025-04-18', 'Active', 9),
('EMP280', 'Nika', 'Noun', 'Female', '012345957', 'nika.noun@company.com', 'Associate Product Mgr', '2025-05-12', 'Active', 10),
('EMP281', 'Thida', 'Srun', 'Female', '012345958', 'thida.srun@company.com', 'HR Intern', '2026-05-15', 'Active', 1),
('EMP282', 'Odom', 'Phon', 'Male', '012345959', 'odom.phon@company.com', 'Finance Clerk', '2026-01-10', 'Active', 2),
('EMP283', 'Maneth', 'Chon', 'Male', '012345960', 'maneth.chon@company.com', 'Software Engineer', '2025-09-05', 'Active', 3),
('EMP284', 'Sophal', 'Suon', 'Male', '012345961', 'sophal.suon@company.com', 'Content Specialist', '2025-11-20', 'Active', 4),
('EMP285', 'Leakhena', 'Seng', 'Female', '012345962', 'leakhena.seng@company.com', 'Sales Associate', '2025-08-11', 'Active', 5),
('EMP286', 'Seyha', 'Un', 'Male', '012345963', 'seyha.un@company.com', 'Support Specialist', '2026-07-10', 'Active', 6),
('EMP287', 'Sovan', 'Touch', 'Male', '012345964', 'sovan.touch@company.com', 'Chemical Technician', '2024-02-15', 'Active', 7),
('EMP288', 'Somally', 'Prum', 'Female', '012345965', 'somally.prum@company.com', 'Compliance Analyst', '2025-03-01', 'Active', 8),
('EMP289', 'Panha', 'Ouk', 'Male', '012345966', 'panha.ouk@company.com', 'Supply Chain Analyst', '2025-06-15', 'Active', 9),
('EMP290', 'Serey', 'Khim', 'Male', '012345967', 'serey.khim@company.com', 'Product Designer', '2024-12-10', 'Active', 10),
('EMP291', 'Phalla', 'Ros', 'Female', '012345968', 'phalla.ros@company.com', 'Recruiter', '2026-03-05', 'Active', 1),
('EMP292', 'Sophanny', 'Lim', 'Female', '012345969', 'sophanny.lim@company.com', 'Junior Accountant', '2025-10-12', 'Active', 2),
('EMP293', 'Sreyroth', 'Ros', 'Female', '012345970', 'sreyroth.ros@company.com', 'Backend Developer', '2025-11-01', 'Active', 3),
('EMP294', 'Narin', 'Nhem', 'Male', '012345971', 'narin.nhem@company.com', 'Marketing Specialist', '2025-07-22', 'Active', 4),
('EMP295', 'Vannith', 'Pech', 'Male', '012345972', 'vannith.pech@company.com', 'Sales Supervisor', '2024-10-05', 'Active', 5),
('EMP296', 'Somally', 'Oung', 'Female', '012345973', 'somally.oung@company.com', 'Customer Support Rep', '2026-07-15', 'Active', 6),
('EMP297', 'Sreyneang', 'Srun', 'Female', '012345974', 'sreyneang.srun@company.com', 'R&D Engineer', '2025-09-10', 'Active', 7),
('EMP298', 'Makara', 'Ang', 'Male', '012345975', 'makara.ang@company.com', 'Legal Counsel', '2025-12-01', 'Active', 8),
('EMP299', 'Boramy', 'Chhoeun', 'Female', '012345976', 'boramy.chhoeun@company.com', 'Inventory Specialist', '2025-04-20', 'Active', 9),
('EMP300', 'Sothea', 'Koeut', 'Male', '012345977', 'sothea.koeut@company.com', 'Product Manager', '2024-08-14', 'Active', 10);

DO $$
DECLARE
    emp_id INT;
    att_date DATE := '2025-01-01'; -- ចាប់ផ្ដើមពីដើមឆ្នាំ 2025 ដើម្បីឱ្យមានថ្ងៃគ្រាន់សម្រាប់ទិន្នន័យ 10k
    counter INT := 0;
    rand_val NUMERIC;
    c_in TIME;
    c_out TIME;
    att_status VARCHAR(20);
BEGIN
    -- រត់ Loop រហូតដល់គ្រប់ 10,000 ទិន្នន័យ
    WHILE counter < 10000 LOOP
        -- ច្រោះយកតែថ្ងៃធ្វើការ ចន្ទ ដល់ សុក្រ (ISO DOW: 1=Mon, 5=Fri)
        IF EXTRACT(ISODOW FROM att_date) IN (1, 2, 3, 4, 5) THEN
            
            -- ក្នុងមួយថ្ងៃៗ ជ្រើសរើសបុគ្គលិកចំនួន 30 នាក់ដោយចៃដន្យ (Random) យកមកបង្កើតវត្តមាន
            FOR emp_id IN (SELECT employee_id FROM employees ORDER BY random() LIMIT 30) LOOP
                IF counter >= 10000 THEN
                    EXIT;
                END IF;

                rand_val := random();

                IF rand_val < 0.08 THEN
                    -- អវត្តមាន (Absent ~ 8%)
                    c_in := NULL;
                    c_out := NULL;
                    att_status := 'Absent';
                ELSIF rand_val < 0.25 THEN
                    -- មកយឺត (Late ~ 17% - ចូលចន្លោះម៉ោង 8:31 ដល់ 9:15)
                    c_in := '08:30:00'::TIME + (random() * '45 minutes'::INTERVAL);
                    c_out := '17:00:00'::TIME + (random() * '90 minutes'::INTERVAL);
                    att_status := 'Late';
                ELSE
                    -- មកទាន់ពេល (Present ~ 75% - ចូលចន្លោះម៉ោង 7:30 ដល់ 8:30)
                    c_in := '07:30:00'::TIME + (random() * '60 minutes'::INTERVAL);
                    c_out := '17:00:00'::TIME + (random() * '90 minutes'::INTERVAL);
                    att_status := 'Present';
                END IF;

                -- បញ្ចូលទិន្នន័យទៅក្នុងតារាង
                INSERT INTO attendance (employee_id, attendance_date, check_in, check_out, status)
                VALUES (emp_id, att_date, c_in, c_out, att_status);

                counter := counter + 1;
            END LOOP;
        END IF;
        
        -- បូកបន្ថែម 1 ថ្ងៃបន្ទាប់
        att_date := att_date + INTERVAL '1 day';
    END LOOP;
END $$;

INSERT INTO attendance (employee_id, attendance_date, check_in, check_out, status) VALUES
(1, '2026-01-01', '08:15:00', '17:05:00', 'Present'),
(2, '2026-01-01', '08:45:00', '17:30:00', 'Late'),
(3, '2026-01-01', NULL, NULL, 'Absent'),
(4, '2026-01-01', '07:55:00', '17:10:00', 'Present'),
(5, '2026-01-01', '08:02:00', '18:00:00', 'Present'),
(6, '2026-01-01', '08:50:00', '17:00:00', 'Late'),
(7, '2026-01-01', '07:45:00', '17:15:00', 'Present'),
(8, '2026-01-01', NULL, NULL, 'Absent'),
(9, '2026-01-01', '08:20:00', '17:45:00', 'Present'),
(10, '2026-01-01', '08:35:00', '17:00:00', 'Late'),
-- ទិន្នន័យបន្តបន្ទាប់សម្រាប់ថ្ងៃទី 2...
(1, '2026-01-02', '07:50:00', '17:15:00', 'Present'),
(2, '2026-01-02', '08:05:00', '17:00:00', 'Present'),
(3, '2026-01-02', '08:55:00', '17:40:00', 'Late'),
(4, '2026-01-02', '08:10:00', '17:02:00', 'Present'),
(5, '2026-01-02', NULL, NULL, 'Absent');
-- (អ្នកអាចដំណើរការវិធីសាស្ត្រទី 1 ខាងលើ ដើម្បីទទួលបានទិន្នន័យពេញលេញ 2000 ជួរភ្លាមៗដោយមិនចាំបាច់សរសេរកូដវាយវែងអន្លាយ)

DO $$
DECLARE
    emp_id INT;
    today_date DATE := '2026-07-15'; -- ថ្ងៃនេះផ្ទាល់
    counter INT := 0;
    rand_val NUMERIC;
    c_in TIME;
    c_out TIME;
    att_status VARCHAR(20);
BEGIN
    -- លុបទិន្នន័យចាស់ដែលធ្លាប់មានសម្រាប់ថ្ងៃនេះចេញសិន ចៀសវាងការជាន់គ្នា (Optional)
    DELETE FROM attendance WHERE attendance_date = today_date;

    -- រើសបុគ្គលិកចំនួន 100 នាក់ដោយចៃដន្យ ដើម្បីបង្កើតវត្តមានសម្រាប់ថ្ងៃនេះ
    FOR emp_id IN (SELECT employee_id FROM employees ORDER BY random() LIMIT 100) LOOP
        
        rand_val := random();

        IF rand_val < 0.05 THEN
            -- អវត្តមាន (Absent 5%)
            c_in := NULL;
            c_out := NULL;
            att_status := 'Absent';
        ELSIF rand_val < 0.20 THEN
            -- មកយឺត (Late 15% - ចូលចន្លោះម៉ោង 8:31 ដល់ 9:15)
            c_in := '08:30:00'::TIME + (random() * '45 minutes'::INTERVAL);
            c_out := '17:00:00'::TIME + (random() * '90 minutes'::INTERVAL);
            att_status := 'Late';
        ELSE
            -- មកទាន់ពេល (Present 80% - ចូលចន្លោះម៉ោង 7:30 ដល់ 8:30)
            c_in := '07:30:00'::TIME + (random() * '60 minutes'::INTERVAL);
            c_out := '17:00:00'::TIME + (random() * '90 minutes'::INTERVAL);
            att_status := 'Present';
        END IF;

        -- បញ្ចូលទិន្នន័យ
        INSERT INTO attendance (employee_id, attendance_date, check_in, check_out, status)
        VALUES (emp_id, today_date, c_in, c_out, att_status);

    END LOOP;
END $$;

SELECT *
FROM attendance
