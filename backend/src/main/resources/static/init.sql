
-- 创建数据库（如果尚未创建）
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'Backend')
BEGIN
    CREATE DATABASE Backend;
END
GO

USE Backend;

create schema backend;

-- 创建 products 表
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='backend.products' AND xtype='U')
BEGIN
CREATE TABLE backend.products (
                                 Id NVARCHAR(36) PRIMARY KEY, -- UUID 字符串作为主键
                                 Name NVARCHAR(255) NOT NULL,
                                 Price DECIMAL(18, 2) NOT NULL, -- 支持金额类型
                                 Stock INT NOT NULL DEFAULT 0 -- 库存数量，默认为0
);
END
GO

-- 创建 Orders 表
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='backend.orders' AND xtype='U')
BEGIN
CREATE TABLE  backend.orders (
                                 id BIGINT IDENTITY(1,1) PRIMARY KEY, -- 自增主键
                                 productId NVARCHAR(36) NOT NULL,
                                 quantity INT NOT NULL,
                                 totalPrice DECIMAL(18, 2) NOT NULL,
                                 orderTime DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET(), -- 下单时间
                                 FOREIGN KEY (productId) REFERENCES  backend.products(id)
);
END
GO


-- 插入测试数据
INSERT INTO backend.products (id, name, price, stock) VALUES
('123e4567-e89b-12d3-a456-426614174000', 'Laptop', 1200.00, 50),
('123e4567-e89b-12d3-a456-426614174001', 'Smartphone', 800.00, 200),
('123e4567-e89b-12d3-a456-426614174002', 'Wireless Headphones', 150.00, 150),
('123e4567-e89b-12d3-a456-426614174003', 'Mechanical Keyboard', 100.00, 80),
('123e4567-e89b-12d3-a456-426614174004', 'Monitor 24"', 300.00, 120),
('123e4567-e89b-12d3-a456-426614174005', 'Tablet', 400.00, 90),
('123e4567-e89b-12d3-a456-426614174006', 'Gaming Mouse', 50.00, 200),
('123e4567-e89b-12d3-a456-426614174007', 'USB-C Hub', 45.00, 100),
('123e4567-e89b-12d3-a456-426614174008', 'External SSD 1TB', 200.00, 60),
('123e4567-e89b-12d3-a456-426614174009', 'Bluetooth Speaker', 70.00, 180);