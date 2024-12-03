var spec = {
    swagger: "2.0",    // Phiên bản Swagger UI
    info: {
        description: "Các thông tin mô tả về dự án và API",
        version: "1.0",    // Phiên bản API
        title: "Detissue"
    },
    host: "localhost:3000",    // Server và port deploy API
    basePath: "/api/v1",       // Đường dẫn tới API
    tags: [    // Danh sách các nhóm API: admin, users, images,...
        {
            name: "user",                                   // Tên nhóm API
            description: "Các API về người dùng"         // Mô tả về nhóm API
        }
    ],
    schemes: ["http"],    // Sử dụng scheme gì? HTTP, HTTPS?
    paths: {
        "/user/signup": {    // Đường dẫn cho đăng ký người dùng
            post: {        // Phương thức gửi request: get, post, put, delete
                tags: ["user"],
                summary: "Đăng ký người dùng mới",
                description: "",
                operationId: "signupUser",
                consumes: ["application/json"],    // Loại dữ liệu gửi đi
                produces: ["application/json"],    // Loại dữ liệu trả về
                parameters: [               // Các tham số
                    {
                        in: "body",      // Tham số được gửi từ body
                        name: "signupRequest",    // Tên tham số
                        required: true,    // Tham số là bắt buộc
                        schema: {
                            type: "object",
                            properties: {
                                username: {
                                    type: "string",   // Loại dữ liệu của tham số là chuỗi
                                    description: "Tên người dùng",
                                    example: "nguyenvanc" // Thêm giá trị ví dụ
                                },
                                password: {
                                    type: "string",   // Loại dữ liệu của tham số là chuỗi
                                    description: "Mật khẩu",
                                    example: "12345678" // Thêm giá trị ví dụ
                                },
                                email: {
                                    type: "string",
                                    description: "Email của người dùng",
                                    example: "nguyenvan@gmail.com"
                                },
                                fullname: {
                                    type: "string",
                                    description: "Họ và tên",
                                    example: "Nguyen Van C"
                                },
                                phone: {
                                    type: "string",
                                    description: "Số điện thoại",
                                    example: "0912345678"
                                }
                            },
                            required: ["username", "password", "email", "fullname", "phone"] // Các trường bắt buộc
                        }
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về thông tin người dùng đã đăng ký",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 500
                                },
                                message: {
                                    type: "string",
                                    example: "Username existed"
                                },
                                data: {
                                    example: "null"
                                }
                            }
                        }
                    },
                    400: {
                        description: "Lỗi xác thực dữ liệu"
                    }
                },
                security: [
                    // Thêm thông tin bảo mật nếu cần
                ]
            }
        },
        "/user/signin": {    // Đường dẫn cho đăng nhập người dùng
    post: {
        tags: ["user"],
        summary: "Đăng nhập người dùng",
        description: "Đăng nhập bằng tên người dùng và mật khẩu.",
        operationId: "signinUser",
        consumes: ["application/x-www-form-urlencoded"], // Loại dữ liệu gửi đi
        produces: ["application/json"],                 // Loại dữ liệu trả về
        parameters: [
            {
                "name": "username",
                "type": "string",
                "description": "Tên người dùng",
                "example": "nguyenvana"  // Thêm giá trị ví dụ
            },
            {
                "name": "password",
                "type": "string",
                "description": "Mật khẩu",
                "example": "12345678"  // Thêm giá trị ví dụ
            }
        ],
        responses: {
            200: {
                description: "Trả về thông tin người dùng đã đăng nhập",
                schema: {
                    type: "object",
                    properties: {
                        statusCode: {
                            type: "integer",
                            example: 200
                        },
                        message: {
                            type: "string",
                            example: "Đăng nhập thành công"
                        },
                        data: {
                            type: "string",
                            example: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                        }
                    }
                }
            },
            401: {
                description: "Thông tin đăng nhập không hợp lệ"
            }
        },
        security: []
    }
},
        "/user/address": {    // Đường dẫn để lấy địa chỉ của người dùng
            post: {
                tags: ["user"],
                summary: "Lấy địa chỉ của người dùng",
                description: "",
                operationId: "findAllAddressesByUserId",
                consumes: [],
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "body",
                        "name": "address",
                        "required": true,
                        "schema": {
                            type: "object",
                            properties: {
                                street_address: {
                                    type: "string",
                                    example: "349 hung vuong"
                                },
                                town_city: {
                                    type: "string",
                                    example: "Di Linh"
                                },
                                company: {
                                    type: "string",
                                    example: "not company"
                                },
                                countryId: {
                                    type: "integer",
                                    example: 2
                                }
                            },
                            required: ["street_address", "town_city", "countryId"] // Các trường bắt buộc
                        },
                        description: "Thông tin địa chỉ người dùng"
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về danh sách địa chỉ của người dùng hoặc thông báo lỗi.",
                schema: {
                    type: "object",
                    properties: {
                        statusCode: {
                            type: "integer",
                            example: 500
                        },
                        message: {
                            type: "string",
                            example: "Error addAddress in AddressService attempted to assign id from null one-to-one property [com.DIY.Detissue.entity.UserAddress.address]"
                        },
                        data: {
                            type: "string",
                            example: "null"
                        }
                    }
                }
            },
                    401: {
                        description: "Token không hợp lệ"
                    }
                },
                security: []
            }
        },
        "/user/detail": {    // Đường dẫn để lấy thông tin chi tiết của người dùng
    get: {
        tags: ["user"],
        summary: "Lấy thông tin người dùng",
        description: "Trả về thông tin chi tiết của người dùng.",
        operationId: "getUserDetail",
        consumes: [],
        produces: ["application/json"],
        parameters: [
            {
                "in": "header",
                "name": "Authorization",
                "required": true,
                "type": "string",
                "description": "Token xác thực người dùng (Bearer token)"
            }
        ],
        responses: {
            200: {
                description: "Trả về thông tin chi tiết của người dùng",
                schema: {
                    type: "object",
                    properties: {
                        statusCode: {
                            type: "integer",
                            example: 200
                        },
                        message: {
                            type: "string",
                            example: null
                        },
                        data: {
                            type: "object",
                            properties: {
                                id: {
                                    type: "integer",
                                    example: 1
                                },
                                fullname: {
                                    type: "string",
                                    example: "Nguyen Van A"
                                },
                                email: {
                                    type: "string",
                                    example: "nguyenvana@gmail.com"
                                },
                                phone: {
                                    type: "string",
                                    example: "0123456789"
                                },
                                birthDay: {
                                    type: "string",
                                    format: "date",
                                    example: null
                                },
                                createTime: {
                                    type: "string",
                                    format: "date-time",
                                    example: "2024-05-29 21:39:11.0"
                                },
                                lastLoginTime: {
                                    type: "string",
                                    format: "date-time",
                                    example: "2024-12-02 15:13:00"
                                },
                                username: {
                                    type: "string",
                                    example: "nguyenvana"
                                }
                            },
                            required: ["id", "fullname", "email", "phone", "createTime", "lastLoginTime", "username"]
                        }
                    }
                }
            },
                    401: {
                        description: "Token không hợp lệ"
                    }
                },
                security: []
            }
        },
        "/user/update": {    // Đường dẫn để cập nhật thông tin người dùng
    post: {
        tags: ["user"],
        summary: "Cập nhật thông tin người dùng",
        description: "Cập nhật thông tin chi tiết của người dùng.",
        operationId: "updateUser",
        consumes: ["application/json"],
        produces: ["application/json"],
        parameters: [
            {
                "in": "body",
                "name": "userRequest",
                "required": true,
                "schema": {
                    "type": "object",
                    "properties": {
                        "username": {
                            "type": "string",
                            "description": "Tên người dùng",
                            "example": "nguyenvana"
                        },
                        "password": {
                            "type": "string",
                            "description": "Mật khẩu mới",
                            "example": "12345678"
                        },
                        "email": {
                            "type": "string",
                            "description": "Địa chỉ email",
                            "example": "nguyenvana@gmail.com"
                        },
                        "phone": {
                            "type": "string",
                            "description": "Số điện thoại",
                            "example": "0123456789"
                        },
                        "fullname": {
                            "type": "string",
                            "description": "Họ và tên",
                            "example": "Nguyen Van A"
                        },
                        "birthDay": {
                            "type": "string",
                            "format": "date",
                            "description": "Ngày sinh",
                            "example": "2003-06-13"
                        }
                    },
                    required: ["username", "password", "email", "phone", "fullname", "birthDay"] // Các trường bắt buộc
                }
            }
        ],
                responses: {
                    200: {
                        description: "Trả về thông tin người dùng đã cập nhật",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: "null"
                                },
                                data: {
                                    type: "string",
                                    example: "true"
                                }
                            }
                        }
                    },
                    400: {
                        description: "Lỗi xác thực dữ liệu"
                    },
                    401: {
                        description: "Token không hợp lệ"
                    }
                },
                security: []
            }
        },
    
        "/product/{all}": {    // Đường dẫn để lấy tất cả sản phẩm
            get: {
                tags: ["product"],
                summary: "Hiển thị tất cả sản phẩm",
                description: "",
                operationId: "findAllProduct",
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "query",
                        "name": "page",
                        "required": true,
                        "type": "integer",
                        "description": "Số trang để phân trang. Giá trị bắt đầu từ 0.",
                        "example": 0
                    },
                    {
                        "in": "query",
                        "name": "size",
                        "required": true,
                        "type": "integer",
                        "description": "Số lượng sản phẩm trên mỗi trang.",
                        "example": 10
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về danh sách địa chỉ của người dùng",
                schema: {
                    type: "object",
                    properties: {
                        statusCode: {
                            type: "integer",
                            example: 200
                        },
                        message: {
                            type: "string",
                            nullable: true,
                            example: null
                        },
                        data: {
                            type: "array",
                            items: {
                                type: "object",
                                properties: {
                                    id: {
                                        type: "integer",
                                        example: 1
                                    },
                                    name: {
                                        type: "string",
                                        example: "Cotton Sleeveless T-Shirt"
                                    },
                                    shortDesc: {
                                        type: "string",
                                        example: "Made with Recycled Materials"
                                    },
                                    fullDesc: {
                                        type: "string",
                                        nullable: true,
                                        example: "empty"
                                    },
                                    image: {
                                        type: "array",
                                        items: {
                                            type: "string",
                                            example: "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream.webp"
                                        }
                                    },
                                    priceMax: {
                                        type: "integer",
                                        example: 50
                                    },
                                    priceMin: {
                                        type: "integer",
                                        example: 20
                                    },
                                    category: {
                                        type: "string",
                                        example: "Clothing"
                                    }
                                }
                            },
                            example: [
                                {
                                    "id": 1,
                                    "name": "Cotton Sleeveless T-Shirt",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": "empty",
                                    "image": [
                                        "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream.webp",
                                        "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream-2.webp",
                                        "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream-3.webp"
                                    ],
                                    "priceMax": 50,
                                    "priceMin": 20,
                                    "category": "Clothing"
                                },
                                {
                                    "id": 4,
                                    "name": "Black Shirt",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": null,
                                    "image": [
                                        "assets/images/Kien/black-shirt-1.jpg",
                                        "assets/images/Kien/black-shirt-2.jpg",
                                        "assets/images/Kien/black-shirt-3.jpg"
                                    ],
                                    "priceMax": 40,
                                    "priceMin": 10,
                                    "category": "Clothing"
                                },
                                {
                                    "id": 5,
                                    "name": "Gold Necklace",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": null,
                                    "image": [
                                        "assets/images/Kien/gold-necklace-1.jpg",
                                        "assets/images/Kien/gold-necklace-2.jpg",
                                        "assets/images/Kien/gold-necklace-3.jpg"
                                    ],
                                    "priceMax": 80,
                                    "priceMin": 70,
                                    "category": "Accessories"
                                },
                                {
                                    "id": 6,
                                    "name": "Linen Boxy Shirt",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": null,
                                    "image": [
                                        "assets/images/Kien/linen-boxy-shirt-1.jpg",
                                        "assets/images/Kien/linen-boxy-shirt-2_1.jpg",
                                        "assets/images/Kien/linen-boxy-shirt-3_1.jpg"
                                    ],
                                    "priceMax": 120,
                                    "priceMin": 90,
                                    "category": "Clothing"
                                },
                                {
                                    "id": 7,
                                    "name": "New Balance",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": null,
                                    "image": [
                                        "assets/images/Kien/new-balance550-1.jpg",
                                        "assets/images/Kien/new-balance550-2.jpg",
                                        "assets/images/Kien/new-balance1906-3.jpg"
                                    ],
                                    "priceMax": 210,
                                    "priceMin": 150,
                                    "category": "Best Seller"
                                },
                                {
                                    "id": 8,
                                    "name": "Striped Shirt",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": null,
                                    "image": [
                                        "assets/images/Kien/striped-shirt-1.jpg",
                                        "assets/images/Kien/striped-shirt-2.jpg",
                                        "assets/images/Kien/striped-shirt-3.jpg"
                                    ],
                                    "priceMax": 80,
                                    "priceMin": 70,
                                    "category": "Clothing"
                                },
                                {
                                    "id": 9,
                                    "name": "Red-Cuban",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": null,
                                    "image": [
                                        "assets/images/Kien/red-cuban-1.jpg",
                                        "assets/images/Kien/red-cuban-2.jpg",
                                        "assets/images/Kien/red-cuban-3.jpg"
                                    ],
                                    "priceMax": 110,
                                    "priceMin": 100,
                                    "category": "Best Seller"
                                },
                                {
                                    "id": 10,
                                    "name": "Silver Chain Bracelet",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": null,
                                    "image": [
                                        "assets/images/Kien/silver-chain-bracelet-1.jpg",
                                        "assets/images/Kien/silver-chain-bracelet-2.jpg",
                                        "assets/images/Kien/silver-chain-bracelet-3.jpg"
                                    ],
                                    "priceMax": 50,
                                    "priceMin": 40,
                                    "category": "Accessories"
                                }
                            ]
                        }
                    }
                }
            }
        },
                security: []
            }
        },
        "/product/{id}": {    // Đường dẫn để lấy sản phẩm theo ID
            get: {
                tags: ["product"],
                summary: "Lấy sản phẩm theo ID",
                description: "",
                operationId: "findProductById",
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "path",
                        "name": "id",
                        "required": true,
                        "type": "integer",
                        "description": "ID của sản phẩm"
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về thông tin sản phẩm",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    nullable: true,
                                    example: null
                                },
                                data: {
                                    type: "object",
                                    properties: {
                                        id: {
                                            type: "integer",
                                            example: 1
                                        },
                                        name: {
                                            type: "string",
                                            example: "Cotton Sleeveless T-Shirt"
                                        },
                                        shortDesc: {
                                            type: "string",
                                            example: "Made with Recycled Materials"
                                        },
                                        fullDesc: {
                                            type: "string",
                                            nullable: true,
                                            example: "empty"
                                        },
                                        image: {
                                            type: "array",
                                            items: {
                                                type: "string",
                                                example: "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream.webp"
                                            }
                                        },
                                        priceMax: {
                                            type: "integer",
                                            example: 50
                                        },
                                        priceMin: {
                                            type: "integer",
                                            example: 20
                                        },
                                        category: {
                                            type: "string",
                                            example: "Clothing"
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                security: []
            }
        },
        "/product/category": {    // Đường dẫn để lấy sản phẩm theo danh mục
            get: {
                tags: ["product"],
                summary: "Lấy sản phẩm theo danh mục",
                description: "",
                operationId: "findProductByCategory",
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "query",
                        "name": "page",
                        "required": true,
                        "type": "integer",
                        "description": "Số trang để phân trang",
                        "example": 0  // Thêm giá trị ví dụ
                    },
                    {
                        "in": "query",
                        "name": "size",
                        "required": true,
                        "type": "integer",
                        "description": "Số lượng sản phẩm trên mỗi trang",
                        "example": 5  // Thêm giá trị ví dụ
                    },
                    {
                        "in": "query",
                        "name": "id",
                        "required": true,
                        "type": "integer",
                        "description": "ID của danh mục sản phẩm",
                        "example": 1  // Thêm giá trị ví dụ
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về danh sách sản phẩm theo danh mục",
                schema: {
                    type: "object",
                    properties: {
                        statusCode: {
                            type: "integer",
                            example: 200
                        },
                        message: {
                            type: "string",
                            nullable: true,
                            example: null
                        },
                        data: {
                            type: "array",
                            items: {
                                type: "object",
                                properties: {
                                    id: {
                                        type: "integer",
                                        example: 1
                                    },
                                    name: {
                                        type: "string",
                                        example: "Cotton Sleeveless T-Shirt"
                                    },
                                    shortDesc: {
                                        type: "string",
                                        example: "Made with Recycled Materials"
                                    },
                                    fullDesc: {
                                        type: "string",
                                        nullable: true,
                                        example: "empty"
                                    },
                                    image: {
                                        type: "array",
                                        items: {
                                            type: "string",
                                            example: "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream.webp"
                                        }
                                    },
                                    priceMax: {
                                        type: "integer",
                                        example: 50
                                    },
                                    priceMin: {
                                        type: "integer",
                                        example: 20
                                    },
                                    category: {
                                        type: "string",
                                        example: "Clothing"
                                    }
                                }
                            },
                            example: [
                                {
                                    "id": 1,
                                    "name": "Cotton Sleeveless T-Shirt",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": "empty",
                                    "image": [
                                        "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream.webp",
                                        "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream-2.webp",
                                        "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream-3.webp"
                                    ],
                                    "priceMax": 50,
                                    "priceMin": 20,
                                    "category": "Clothing"
                                },
                                {
                                    "id": 4,
                                    "name": "Black Shirt",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": null,
                                    "image": [
                                        "assets/images/Kien/black-shirt-1.jpg",
                                        "assets/images/Kien/black-shirt-2.jpg",
                                        "assets/images/Kien/black-shirt-3.jpg"
                                    ],
                                    "priceMax": 40,
                                    "priceMin": 10,
                                    "category": "Clothing"
                                },
                                {
                                    "id": 6,
                                    "name": "Linen Boxy Shirt",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": null,
                                    "image": [
                                        "assets/images/Kien/linen-boxy-shirt-1.jpg",
                                        "assets/images/Kien/linen-boxy-shirt-2_1.jpg",
                                        "assets/images/Kien/linen-boxy-shirt-3_1.jpg"
                                    ],
                                    "priceMax": 120,
                                    "priceMin": 90,
                                    "category": "Clothing"
                                },
                                {
                                    "id": 8,
                                    "name": "Striped Shirt",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": null,
                                    "image": [
                                        "assets/images/Kien/striped-shirt-1.jpg",
                                        "assets/images/Kien/striped-shirt-2.jpg",
                                        "assets/images/Kien/striped-shirt-3.jpg"
                                    ],
                                    "priceMax": 80,
                                    "priceMin": 70,
                                    "category": "Clothing"
                                }
                            ]
                        }
                    }
                }
            }
        },
                security: []
            }
        },
        "/product/search": {    // Đường dẫn để tìm kiếm sản phẩm
            get: {
                tags: ["product"],
                summary: "Tìm kiếm sản phẩm",
                description: "",
                operationId: "searchProduct",
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "query",
                        "name": "page",
                        "required": true,
                        "type": "integer",
                        "description": "Số trang để phân trang",
                        "value": 0
                    },
                    {
                        "in": "query",
                        "name": "size",
                        "required": true,
                        "type": "integer",
                        "description": "Số lượng sản phẩm trên mỗi trang",
                        "value": 3
                    },
                    {
                        "in": "query",
                        "name": "keyword",
                        "required": true,
                        "type": "string",
                        "description": "Từ khóa tìm kiếm sản phẩm",
                        "value": "Shirt"
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về danh sách sản phẩm theo danh mục",
                schema: {
                    type: "object",
                    properties: {
                        statusCode: {
                            type: "integer",
                            example: 200
                        },
                        message: {
                            type: "string",
                            nullable: true,
                            example: null
                        },
                        data: {
                            type: "array",
                            items: {
                                type: "object",
                                properties: {
                                    id: {
                                        type: "integer",
                                        example: 1
                                    },
                                    name: {
                                        type: "string",
                                        example: "Cotton Sleeveless T-Shirt"
                                    },
                                    shortDesc: {
                                        type: "string",
                                        example: "Made with Recycled Materials"
                                    },
                                    fullDesc: {
                                        type: "string",
                                        nullable: true,
                                        example: "empty"
                                    },
                                    image: {
                                        type: "array",
                                        items: {
                                            type: "string",
                                            example: "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream.webp"
                                        }
                                    },
                                    priceMax: {
                                        type: "integer",
                                        example: 50
                                    },
                                    priceMin: {
                                        type: "integer",
                                        example: 20
                                    },
                                    category: {
                                        type: "string",
                                        example: "Clothing"
                                    }
                                }
                            },
                            example: [
                                {
                                    "id": 1,
                                    "name": "Cotton Sleeveless T-Shirt",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": "empty",
                                    "image": [
                                        "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream.webp",
                                        "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream-2.webp",
                                        "assets/images/Kien/Polo Ralph Lauren icon logo t-shirt in cream-3.webp"
                                    ],
                                    "priceMax": 50,
                                    "priceMin": 20,
                                    "category": "Clothing"
                                },
                                {
                                    "id": 4,
                                    "name": "Black Shirt",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": null,
                                    "image": [
                                        "assets/images/Kien/black-shirt-1.jpg",
                                        "assets/images/Kien/black-shirt-2.jpg",
                                        "assets/images/Kien/black-shirt-3.jpg"
                                    ],
                                    "priceMax": 40,
                                    "priceMin": 10,
                                    "category": "Clothing"
                                },
                                {
                                    "id": 6,
                                    "name": "Linen Boxy Shirt",
                                    "shortDesc": "Made with Recycled Materials",
                                    "fullDesc": null,
                                    "image": [
                                        "assets/images/Kien/linen-boxy-shirt-1.jpg",
                                        "assets/images/Kien/linen-boxy-shirt-2_1.jpg",
                                        "assets/images/Kien/linen-boxy-shirt-3_1.jpg"
                                    ],
                                    "priceMax": 120,
                                    "priceMin": 90,
                                    "category": "Clothing"
                                }
                            ]
                        }
                    }
                }
            }
        },
                security: []
            }
        },
        "/product/add": {    // Đường dẫn để thêm sản phẩm
            post: {
                tags: ["product"],
                summary: "Thêm sản phẩm mới",
                description: "",
                operationId: "createProduct",
                consumes: ["application/json"],
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "body",
                        "name": "createProductRequest",
                        "required": true,
                        "schema": {
                            "type": "object",
                            "properties": {
                                "id": {
                                    "type" : "string",
                                    "example": "1"
                                },
                                "name": {
                                    "type": "string",
                                    "description": "Tên sản phẩm",
                                    "example": "Product Name"
                                },
                                "shortDesc": {
                                    "type": "string",
                                    "description": "Mô tả ngắn về sản phẩm",
                                    "example": "Short Description"
                                },
                                "fullDesc": {
                                    "type": "string",
                                    "description": "Mô tả đầy đủ về sản phẩm",
                                    "example": "Full Description"
                                },
                                "categoryId": {
                                    "type": "integer",
                                    "description": "ID của danh mục sản phẩm",
                                    "example": 2
                                },
                                "productSizes": {
                                    "type": "array",
                                    "items": {
                                        "type": "object",
                                        "properties": {
                                            "sizeId": {
                                                "type": "string",
                                                "description": "ID của kích cỡ sản phẩm",
                                                "example": "1"
                                            },
                                            "quantity": {
                                                "type": "integer",
                                                "description": "Số lượng sản phẩm theo kích cỡ",
                                                "example": 10
                                            },
                                            "price": {
                                                "type": "number",
                                                "description": "Giá sản phẩm theo kích cỡ",
                                                "example": 30
                                            }
                                        },
                                        "required": ["sizeId", "quantity", "price"]
                                    },
                                    "description": "Danh sách các kích cỡ và thông tin liên quan",
                                    "example": [
                                        {
                                            "sizeId": "1",
                                            "quantity": 10,
                                            "price": 30
                                        },
                                        {
                                            "sizeId": "2",
                                            "quantity": 20,
                                            "price": 50
                                        },
                                        {
                                            "sizeId": "3",
                                            "quantity": 30,
                                            "price": 80
                                        }
                                    ]
                                }
                            },
                            "required": ["name", "categoryId", "productSizes"] // Các trường bắt buộc
                        }
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về sản phẩm đã được thêm",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    nullable: true,
                                    example: null
                                },
                                data: {
                                    type: "boolean",
                                    example: true
                                }
                            },
                            required: ["statusCode", "data"] // Các trường bắt buộc
                        }
                    },
                    400: {
                        description: "Lỗi xác thực dữ liệu"
                    }
                },
                security: []
            }
        },
        "/product/delete": {    // Đường dẫn để xóa sản phẩm
            post: {
                tags: ["product"],
                summary: "Xóa sản phẩm",
                description: "",
                operationId: "deleteProduct",
                consumes: [],
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "query",
                        "name": "id",
                        "required": true,
                        "type": "integer",
                        "description": "ID của sản phẩm cần xóa",
                        "example": 11 // Ví dụ về giá trị ID
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về thông báo xóa sản phẩm thành công",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    nullable: true,
                                    example: null
                                },
                                data: {
                                    type: "boolean",
                                    example: true
                                }
                            },
                            required: ["statusCode", "data"] // Các trường bắt buộc
                        }
                    }
                },
                security: []
            }
        },
        "/product/update": {    // Đường dẫn để cập nhật sản phẩm
            post: {
                tags: ["product"],
                summary: "Cập nhật sản phẩm",
                description: "",
                operationId: "updateProduct",
                consumes: ["application/json"],
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "body",
                        "name": "updateProductRequest",
                        "required": true,
                        "schema": {
                            type: "object",
                            properties: {
                                "id": {
                                    "type": "integer",
                                    "description": "ID của sản phẩm",
                                    "example": 1
                                },
                                "name": {
                                    "type": "string",
                                    "description": "Tên sản phẩm",
                                    "example": "Cotton Sleeveless T-Shirt"
                                },
                                "shortDesc": {
                                    "type": "string",
                                    "description": "Mô tả ngắn về sản phẩm",
                                    "example": "Made with Recycled Materials"
                                },
                                "fullDesc": {
                                    "type": "string",
                                    "description": "Mô tả đầy đủ về sản phẩm",
                                    "example": "empty"
                                },
                                "categoryId": {
                                    "type": "integer",
                                    "description": "ID của danh mục sản phẩm",
                                    "example": 1
                                }
                            },
                            required: ["id", "name", "categoryId"] // Các trường bắt buộc
                        }
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về sản phẩm đã được cập nhật",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    nullable: true,
                                    example: null
                                },
                                data: {
                                    type: "boolean",
                                    example: true
                                }
                            },
                            required: ["statusCode", "data"] // Các trường bắt buộc
                        }
                    },
                    400: {
                        description: "Lỗi xác thực dữ liệu"
                    }
                },
                security: []
            }
        },
        "/category": {
            get: {
                tags: ["category"],
                summary: "Hiển thị tất cả danh mục",
                description: "",
                operationId: "findAllCategory",
                produces: ["application/json"],
                responses: {
                    200: {
                        description: "Trả về danh sách danh mục",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: null
                                },
                                data: {
                                    type: "array",
                                    items: {
                                        type: "object",
                                        properties: {
                                            id: {
                                                type: "integer",
                                                example: 1
                                            },
                                            name: {
                                                type: "string",
                                                example: "Clothing"
                                            }
                                        }
                                    },
                                    example: [
                                        {
                                            id: 1,
                                            name: "Clothing"
                                        },
                                        {
                                            id: 2,
                                            name: "Shose"
                                        },
                                        {
                                            id: 3,
                                            name: "Hat"
                                        },
                                        {
                                            id: 4,
                                            name: "Acessories"
                                        },
                                        {
                                            id: 5,
                                            name: "Best Seller"
                                        }
                                    ]
                                }
                            }
                        }
                    }
                },
                security: []
            }
        },
        "/product": {    // Đường dẫn cho lấy SKU sản phẩm
            get: {        // Phương thức gửi request: get
                tags: ["productSkus"],
                summary: "Lấy SKU sản phẩm theo ID sản phẩm",
                description: "Lấy thông tin SKU cho một sản phẩm dựa trên ID sản phẩm.",
                operationId: "findByProductId",
                produces: ["application/json"],    // Loại dữ liệu trả về
                parameters: [               // Các tham số
                    {
                        "in": "query",      // Tham số được gửi từ query string
                        "name": "id",      // Tên tham số
                        "required": true,   // Tham số là bắt buộc
                        "type": "integer",  // Loại dữ liệu của tham số là số nguyên
                        "description": "ID của sản phẩm cần tìm kiếm SKU",
                        "example" : 1
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về thông tin SKU của sản phẩm",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: null
                                },
                                data: {
                                    type: "array",
                                    items: {
                                        type: "object",
                                        properties: {
                                            id: {
                                                type: "integer",
                                                example: 1
                                            },
                                            quantity: {
                                                type: "integer",
                                                example: 10
                                            },
                                            price: {
                                                type: "number",
                                                format: "float",
                                                example: 20.00
                                            },
                                            size: {
                                                type: "string",
                                                example: "S"
                                            }
                                        }
                                    },
                                    example: [
                                        {
                                            id: 1,
                                            quantity: 10,
                                            price: 20,
                                            size: "S"
                                        },
                                        {
                                            id: 2,
                                            quantity: 10,
                                            price: 50,
                                            size: "M"
                                        },
                                        {
                                            id: 3,
                                            quantity: 10,
                                            price: 30,
                                            size: "L"
                                        }
                                    ]
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ, ID sản phẩm không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: [
                    // Thêm thông tin bảo mật nếu cần
                ]
            }
        },
        "/size/product": {    // Đường dẫn cho lấy kích thước sản phẩm
            get: {            // Phương thức gửi request: get
                tags: ["size"],
                summary: "Lấy kích thước sản phẩm theo ID sản phẩm",
                description: "Lấy thông tin kích thước cho một sản phẩm dựa trên ID sản phẩm.",
                operationId: "findByProductId",
                produces: ["application/json"],    // Loại dữ liệu trả về
                parameters: [
                    {
                        "in": "query",      // Tham số được gửi từ query string
                        "name": "id",      // Tên tham số
                        "required": true,   // Tham số là bắt buộc
                        "type": "integer",  // Loại dữ liệu của tham số là số nguyên
                        "description": "ID của sản phẩm cần tìm kiếm kích thước",
                        "example" : 1 
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về thông tin kích thước của sản phẩm",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: null
                                },
                                data: {
                                    type: "array",
                                    items: {
                                        type: "object",
                                        properties: {
                                            id: {
                                                type: "integer",
                                                example: 1
                                            },
                                            name: {
                                                type: "string",
                                                example: "S"
                                            }
                                        }
                                    },
                                    example: [
                                        {
                                            id: 1,
                                            name: "S"
                                        },
                                        {
                                            id: 2,
                                            name: "M"
                                        },
                                        {
                                            id: 3,
                                            name: "L"
                                        }
                                    ]
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ, ID sản phẩm không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/wishlist/user": {    // Đường dẫn cho lấy danh sách yêu thích của người dùng
            get: {            // Phương thức gửi request: get
                tags: ["userWishlist"],
                summary: "Lấy danh sách yêu thích của người dùng",
                description: "Lấy danh sách sản phẩm yêu thích của người dùng dựa trên ID người dùng.",
                operationId: "findUserWishListByUserId",
                produces: ["application/json"],    // Loại dữ liệu trả về
                parameters: [
                    {
                        "in": "header",
                        "name": "Authorization",
                        "required": true,
                        "type": "string",
                        "description": "Token xác thực của người dùng"
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về danh sách sản phẩm yêu thích",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: null
                                },
                                data: {
                                    type: "array",
                                    items: {
                                        type: "object",
                                        properties: {
                                            id: {
                                                type: "integer",
                                                example: 6
                                            },
                                            name: {
                                                type: "string",
                                                example: "Linen Boxy Shirt"
                                            },
                                            shortDesc: {
                                                type: "string",
                                                example: "Made with Recycled Materials"
                                            },
                                            fullDesc: {
                                                type: "string",
                                                example: null
                                            },
                                            image: {
                                                type: "array",
                                                items: {
                                                    type: "string",
                                                    example: "assets/images/Kien/linen-boxy-shirt-1.jpg"
                                                }
                                            },
                                            priceMax: {
                                                type: "number",
                                                example: 120
                                            },
                                            priceMin: {
                                                type: "number",
                                                example: 90
                                            },
                                            category: {
                                                type: "string",
                                                example: "Clothing"
                                            }
                                        }
                                    },
                                    example: [
                                        {
                                            id: 6,
                                            name: "Linen Boxy Shirt",
                                            shortDesc: "Made with Recycled Materials",
                                            fullDesc: null,
                                            image: [
                                                "assets/images/Kien/linen-boxy-shirt-1.jpg",
                                                "assets/images/Kien/linen-boxy-shirt-2_1.jpg",
                                                "assets/images/Kien/linen-boxy-shirt-3_1.jpg"
                                            ],
                                            priceMax: 120,
                                            priceMin: 90,
                                            category: "Clothing"
                                        }
                                    ]
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/wishlist/delete": {    // Đường dẫn cho xóa sản phẩm khỏi danh sách yêu thích
            get: {
                tags: ["userWishlist"],
                summary: "Xóa sản phẩm khỏi danh sách yêu thích",
                description: "Xóa sản phẩm khỏi danh sách yêu thích của người dùng.",
                operationId: "deleteUserWishlist",
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "header",
                        "name": "Authorization",
                        "required": true,
                        "type": "string",
                        "description": "Token xác thực của người dùng"
                    },
                    {
                        "in": "query",
                        "name": "productId",
                        "required": true,
                        "type": "integer",
                        "description": "ID của sản phẩm cần xóa",
                        "example" : 1
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về thông tin sản phẩm đã xóa khỏi danh sách yêu thích",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 500
                                },
                                message: {
                                    type: "string",
                                    example: "Error deleteProductFromWishlist in ProductService Product not found in wishlist"
                                },
                                data: {
                                    type: "null",
                                    example: null
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/wishlist/add": {    // Đường dẫn cho thêm sản phẩm vào danh sách yêu thích
            get: {
                tags: ["userWishlist"],
                summary: "Thêm sản phẩm vào danh sách yêu thích",
                description: "Thêm sản phẩm vào danh sách yêu thích của người dùng.",
                operationId: "addUserWishlist",
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "header",
                        "name": "Authorization",
                        "required": true,
                        "type": "string",
                        "description": "Token xác thực của người dùng"
                    },
                    {
                        "in": "query",
                        "name": "productId",
                        "required": true,
                        "type": "integer",
                        "description": "ID của sản phẩm cần thêm",
                        "example" : 1
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về thông tin sản phẩm đã thêm vào danh sách yêu thích",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: "null"
                                },
                                data: {
                                    type: "string",
                                    example: "true"
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/cart/user": {    // Đường dẫn cho lấy sản phẩm trong giỏ hàng của người dùng
            get: {            // Phương thức gửi request: get
                tags: ["shoppingCart"],
                summary: "Lấy sản phẩm trong giỏ hàng của người dùng",
                description: "Lấy danh sách sản phẩm trong giỏ hàng dựa trên ID người dùng.",
                operationId: "findShoppingCartItemByUserId",
                produces: ["application/json"],    // Loại dữ liệu trả về
                parameters: [
                    {
                        "in": "header",
                        "name": "Authorization",
                        "required": true,
                        "type": "string",
                        "description": "Token xác thực của người dùng"
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về danh sách sản phẩm trong giỏ hàng",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: null
                                },
                                data: {
                                    type: "array",
                                    items: {
                                        type: "object",
                                        properties: {
                                            id: {
                                                type: "integer",
                                                example: 30
                                            },
                                            name: {
                                                type: "string",
                                                example: "Black Shirt"
                                            },
                                            image: {
                                                type: "array",
                                                items: {
                                                    type: "string",
                                                    example: "assets/images/Kien/black-shirt-1.jpg"
                                                }
                                            },
                                            price: {
                                                type: "number",
                                                example: 40
                                            },
                                            quantity: {
                                                type: "integer",
                                                example: 4
                                            },
                                            size: {
                                                type: "string",
                                                example: "M"
                                            }
                                        }
                                    },
                                    example: [
                                        {
                                            id: 30,
                                            name: "Black Shirt",
                                            image: [
                                                "assets/images/Kien/black-shirt-1.jpg",
                                                "assets/images/Kien/black-shirt-2.jpg",
                                                "assets/images/Kien/black-shirt-3.jpg"
                                            ],
                                            price: 40,
                                            quantity: 4,
                                            size: "M"
                                        }
                                    ]
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/cart/add": {    // Đường dẫn cho thêm sản phẩm vào giỏ hàng
            get: {
                tags: ["shoppingCart"],
                summary: "Thêm sản phẩm vào giỏ hàng",
                description: "Thêm sản phẩm vào giỏ hàng của người dùng.",
                operationId: "addShoppingCartItem",
                consumes: ["application/json"],    // Loại dữ liệu gửi đi
                produces: ["application/json"],    // Loại dữ liệu trả về
                parameters: [
                    {
                        "in": "header",
                        "name": "Authorization",
                        "required": true,
                        "type": "string",
                        "description": "Token xác thực của người dùng"
                    },
                    {
                        "in": "query",
                        "name": "productId",
                        "required": true,
                        "type": "integer",
                        "description": "ID của sản phẩm",
                        "example" : 4
                    },
                    {
                        "in": "query",
                        "name": "quantity",
                        "required": true,
                        "type": "integer",
                        "description": "Số lượng sản phẩm",
                        "example" : 4
                    },
                    {
                        "in": "query",
                        "name": "sizeId",
                        "required": true,
                        "type": "integer",
                        "description": "ID kích thước sản phẩm",
                        "example" : 2
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về thông tin sản phẩm đã thêm vào giỏ hàng",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: "null"
                                },
                                data: {
                                    type: "string",
                                    example: "true"
                                }
                            }
                        }
                    },
                    400: {
                        description: "Lỗi xác thực dữ liệu"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/cart/delete": {    // Đường dẫn cho xóa sản phẩm trong giỏ hàng
            get : {
                tags: ["shoppingCart"],
                summary: "Xóa sản phẩm khỏi giỏ hàng",
                description: "Xóa sản phẩm khỏi giỏ hàng của người dùng.",
                operationId: "deleteShopingCartItemById",
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "header",
                        "name": "Authorization",
                        "required": true,
                        "type": "string",
                        "description": "Token xác thực của người dùng"
                    },
                    {
                        "in": "query",
                        "name": "id",
                        "required": true,
                        "type": "integer",
                        "description": "ID của sản phẩm cần xóa",
                        "example" : 1
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về kết quả xóa sản phẩm khỏi giỏ hàng",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: "Error deleteShopingCartItemById in ShoppingCartItemService ShoppingCartItem not found"
                                },
                                data: {
                                    type: "string",
                                    example: "true"
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/shoporder/user": {    // Đường dẫn cho lấy đơn hàng của người dùng
            get: {            // Phương thức gửi request: get
                tags: ["shopOrder"],
                summary: "Lấy đơn hàng của người dùng",
                description: "Lấy danh sách đơn hàng dựa trên ID người dùng.",
                operationId: "findShoppingCartItemByUserId",
                produces: ["application/json"],    // Loại dữ liệu trả về
                parameters: [
                    {
                        "in": "header",
                        "name": "Authorization",
                        "required": true,
                        "type": "string",
                        "description": "Token xác thực của người dùng"
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về danh sách đơn hàng của người dùng",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: null
                                },
                                data: {
                                    type: "array",
                                    items: {
                                        type: "object",
                                        properties: {
                                            id: {
                                                type: "integer",
                                                example: 1
                                            },
                                            orderTime: {
                                                type: "string",
                                                example: "2024-06-14T20:50:29.000+00:00"
                                            },
                                            status: {
                                                type: "string",
                                                example: "Pending"
                                            },
                                            total_price: {
                                                type: "number",
                                                example: 2000
                                            },
                                            item_count: {
                                                type: "integer",
                                                example: 0
                                            },
                                            userId: {
                                                type: "integer",
                                                example: 1
                                            },
                                            userName: {
                                                type: "string",
                                                example: "nguyenvana"
                                            },
                                            paymentMethod: {
                                                type: "string",
                                                example: "Cash On Delivery"
                                            },
                                            paymentMethodId: {
                                                type: "integer",
                                                example: 1
                                            },
                                            shippingAddress: {
                                                type: "string",
                                                example: "Viet Nam, Di Linh"
                                            },
                                            shippingAddressId: {
                                                type: "integer",
                                                example: 1
                                            }
                                        }
                                    },
                                    example: [
                                        {
                                            id: 1,
                                            orderTime: "2024-06-14T20:50:29.000+00:00",
                                            status: "Pending",
                                            total_price: 2000,
                                            item_count: 0,
                                            userId: 1,
                                            userName: "nguyenvana",
                                            paymentMethod: "Cash On Delivery",
                                            paymentMethodId: 1,
                                            shippingAddress: "Viet Nam, Di Linh",
                                            shippingAddressId: 1
                                        }
                                    ]
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/shoporder/add": {    // Đường dẫn cho thêm đơn hàng
            post: {
                tags: ["shopOrder"],
                summary: "Thêm đơn hàng",
                description: "Thêm một đơn hàng mới cho người dùng.",
                operationId: "addShopOrder",
                consumes: ["application/json"],    // Loại dữ liệu gửi đi
                produces: ["application/json"],    // Loại dữ liệu trả về
                parameters: [
                    {
                        "in": "header",
                        "name": "Authorization",
                        "required": true,
                        "type": "string",
                        "description": "Token xác thực của người dùng"
                    },
                    {
                        "in": "body",
                        "name": "createShopOrderRequest",
                        "required": true,
                        "schema": {
                            type: "object",
                            properties: {
                                paymentMethodId: {
                                    type: "integer",
                                    description: "ID của phương thức thanh toán",
                                    "example" : 1
                                },
                                shippingAddressId: {
                                    type: "integer",
                                    description: "ID của địa chỉ giao hàng",
                                    "example" : 1
                                },
                                note: {
                                    type: "string",
                                    description: "Ghi chú cho đơn hàng",
                                    "example" : "quick"
                                }
                            },
                            required: ["items", "paymentMethodId", "shippingAddressId"] // Các trường bắt buộc
                        }
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về thông tin đơn hàng đã thêm",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: "null"
                                },
                                data: {
                                    type: "string",
                                    example: "true"
                                }
                            }
                        }
                    },
                    400: {
                        description: "Lỗi xác thực dữ liệu"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/shoporder/cancel": {    // Đường dẫn cho hủy đơn hàng
            post: {
                tags: ["shopOrder"],
                summary: "Hủy đơn hàng",
                description: "Hủy một đơn hàng dựa trên ID đơn hàng.",
                operationId: "cancelShopOrder",
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "header",
                        "name": "Authorization",
                        "required": true,
                        "type": "string",
                        "description": "Token xác thực của người dùng"
                    },
                    {
                        "in": "query",
                        "name": "shopOrderId",
                        "required": true,
                        "type": "integer",
                        "description": "ID của đơn hàng cần hủy",
                        "example" : 1
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về kết quả hủy đơn hàng",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: "null"
                                },
                                data: {
                                    type: "string",
                                    example: "true"
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/shoporder/update": {    // Đường dẫn cho cập nhật trạng thái đơn hàng
            post: {
                tags: ["shopOrder"],
                summary: "Cập nhật trạng thái đơn hàng",
                description: "Cập nhật trạng thái của một đơn hàng dựa trên ID đơn hàng và trạng thái mới.",
                operationId: "updateShopOrderStatus",
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "header",
                        "name": "Authorization",
                        "required": true,
                        "type": "string",
                        "description": "Token xác thực của người dùng"
                    },
                    {
                        "in": "query",
                        "name": "shopOrderId",
                        "required": true,
                        "type": "integer",
                        "description": "ID của đơn hàng cần cập nhật",
                        "example" : 1
                    },
                    {
                        "in": "query",
                        "name": "statusId",
                        "required": true,
                        "type": "integer",
                        "description": "ID trạng thái mới",
                        "example" : 1
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về kết quả cập nhật trạng thái đơn hàng",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: "null"
                                },
                                data: {
                                    type: "string",
                                    example: "true"
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/shoporder": {    // Đường dẫn cho lấy tất cả đơn hàng với phân trang
            get: {
                tags: ["shopOrder"],
                summary: "Lấy tất cả đơn hàng",
                description: "Lấy danh sách tất cả đơn hàng với phân trang.",
                operationId: "findAllShopOrder",
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "query",
                        "name": "page",
                        "required": true,
                        "type": "integer",
                        "description": "Số trang cần lấy",
                        "example" : 0
                    },
                    {
                        "in": "query",
                        "name": "size",
                        "required": true,
                        "type": "integer",
                        "description": "Số lượng đơn hàng trên mỗi trang",
                        "example" : 5
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về danh sách đơn hàng",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 500
                                },
                                message: {
                                    type: "string",
                                    example: "Error findAllShopOrder in ShopOrderService Cannot invoke \"com.DIY.Detissue.entity.PaymentMethod.getPaymentType()\" because the return value of \"com.DIY.Detissue.entity.ShopOrder.getPaymentMethod()\" is null"
                                },
                                data: {
                                    type: "string",
                                    example: "null"
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/shoporder/{id}": {    // Đường dẫn cho lấy đơn hàng theo ID
            get: {
                tags: ["shopOrder"],
                summary: "Lấy đơn hàng theo ID",
                description: "Lấy thông tin đơn hàng dựa trên ID.",
                operationId: "findShopOrderById",
                produces: ["application/json"],
                parameters: [
                    {
                        "in": "path",
                        "name": "id",
                        "required": true,
                        "type": "integer",
                        "description": "ID của đơn hàng cần lấy"
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về thông tin đơn hàng",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: null
                                },
                                data: {
                                    type: "array",
                                    items: {
                                        type: "object",
                                        properties: {
                                            id: {
                                                type: "integer",
                                                example: 1
                                            },
                                            orderTime: {
                                                type: "string",
                                                example: "2024-06-14T20:50:29.000+00:00"
                                            },
                                            status: {
                                                type: "string",
                                                example: "Pending"
                                            },
                                            total_price: {
                                                type: "number",
                                                example: 2000
                                            },
                                            item_count: {
                                                type: "integer",
                                                example: 0
                                            },
                                            userId: {
                                                type: "integer",
                                                example: 1
                                            },
                                            userName: {
                                                type: "string",
                                                example: "nguyenvana"
                                            },
                                            paymentMethod: {
                                                type: "string",
                                                example: "Cash On Delivery"
                                            },
                                            paymentMethodId: {
                                                type: "integer",
                                                example: 1
                                            },
                                            shippingAddress: {
                                                type: "string",
                                                example: "Viet Nam, Di Linh"
                                            },
                                            shippingAddressId: {
                                                type: "integer",
                                                example: 1
                                            }
                                        }
                                    },
                                    example: [
                                        {
                                            id: 1,
                                            orderTime: "2024-06-14T20:50:29.000+00:00",
                                            status: "Pending",
                                            total_price: 2000,
                                            item_count: 0,
                                            userId: 1,
                                            userName: "nguyenvana",
                                            paymentMethod: "Cash On Delivery",
                                            paymentMethodId: 1,
                                            shippingAddress: "Viet Nam, Di Linh",
                                            shippingAddressId: 1
                                        }
                                    ]
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/orderstatus": {    // Đường dẫn cho lấy tất cả trạng thái đơn hàng
            get: {            // Phương thức gửi request: get
                tags: ["orderStatus"],
                summary: "Lấy tất cả trạng thái đơn hàng",
                description: "Trả về danh sách tất cả các trạng thái đơn hàng.",
                operationId: "findAllByShopOrderId",
                produces: ["application/json"],    // Loại dữ liệu trả về
                responses: {
                    200: {
                        description: "Trả về danh sách trạng thái đơn hàng",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: null
                                },
                                data: {
                                    type: "array",
                                    items: {
                                        type: "object",
                                        properties: {
                                            id: {
                                                type: "integer",
                                                example: 1
                                            },
                                            status: {
                                                type: "string",
                                                example: "Pending"
                                            }
                                        }
                                    },
                                    example: [
                                        {
                                            status: "Pending",
                                            id: 1
                                        },
                                        {
                                            status: "Processing",
                                            id: 2
                                        },
                                        {
                                            status: "Completed",
                                            id: 3
                                        },
                                        {
                                            status: "Denied",
                                            id: 4
                                        },
                                        {
                                            status: "Cancelled",
                                            id: 5
                                        }
                                    ]
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/review": {    // Đường dẫn cho lấy đánh giá theo ID sản phẩm
            get: {            // Phương thức gửi request: get
                tags: ["review"],
                summary: "Lấy đánh giá theo ID sản phẩm",
                description: "Trả về danh sách đánh giá cho sản phẩm dựa trên ID sản phẩm.",
                operationId: "findByProductId",
                produces: ["application/json"],    // Loại dữ liệu trả về
                parameters: [
                    {
                        "in": "query",
                        "name": "id",
                        "required": true,
                        "type": "integer",
                        "description": "ID của sản phẩm cần lấy đánh giá",
                        "example" : 1
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về danh sách đánh giá cho sản phẩm",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: null
                                },
                                data: {
                                    type: "array",
                                    items: {
                                        type: "object",
                                        properties: {
                                            id: {
                                                type: "integer",
                                                example: 1
                                            },
                                            rating: {
                                                type: "integer",
                                                example: 4
                                            },
                                            review: {
                                                type: "string",
                                                example: "Nice"
                                            },
                                            timeCreated: {
                                                type: "string",
                                                example: null
                                            },
                                            username: {
                                                type: "string",
                                                example: "nguyenvana"
                                            }
                                        }
                                    },
                                    example: [
                                        {
                                            id: 1,
                                            rating: 4,
                                            review: "Nice",
                                            timeCreated: null,
                                            username: "nguyenvana"
                                        },
                                        {
                                            id: 2,
                                            rating: 4,
                                            review: "what a nice product",
                                            timeCreated: "Tue Jun 11 09:25:07 ICT 2024",
                                            username: "nguyenvana"
                                        }
                                    ]
                                }
                            }
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/review/add": {    // Đường dẫn cho thêm đánh giá
            post: {
                tags: ["review"],
                summary: "Thêm đánh giá",
                description: "Thêm một đánh giá mới cho sản phẩm.",
                operationId: "addReview",
                consumes: ["application/json"],    // Loại dữ liệu gửi đi
                produces: ["application/json"],    // Loại dữ liệu trả về
                parameters: [
                    {
                        "in": "header",
                        "name": "Authorization",
                        "required": true,
                        "type": "string",
                        "description": "Token xác thực của người dùng"
                    },
                    {
                        "in": "body",
                        "name": "reviewRequest",
                        "required": true,
                        "schema": {
                            type: "object",
                            properties: {
                                productId: {
                                    type: "integer",
                                    description: "ID của sản phẩm",
                                    example: 1
                                },
                                rating: {
                                    type: "integer",
                                    description: "Đánh giá của người dùng (1-5)",
                                    example: 4
                                },
                                review: {
                                    type: "string",
                                    description: "Nội dung đánh giá",
                                    example: "what a nice product"
                                }
                            },
                            required: ["productId", "rating"] // Các trường bắt buộc
                        }
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về thông tin đánh giá đã thêm",
                        schema: {
                            type: "object",
                            properties: {
                                statusCode: {
                                    type: "integer",
                                    example: 200
                                },
                                message: {
                                    type: "string",
                                    example: "null"
                                },
                                data: {
                                    type: "string",
                                    example: "true"
                                }
                            }
                        }
                    },
                    400: {
                        description: "Lỗi xác thực dữ liệu"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/uploadfile/{fileName}": {    // Đường dẫn cho tải tệp
            get: {            // Phương thức gửi request: get
                tags: ["uploadFile"],
                summary: "Tải tệp theo tên",
                description: "Tải tệp từ server dựa trên tên tệp.",
                operationId: "loadFile",
                produces: ["application/octet-stream"],    // Loại dữ liệu trả về
                parameters: [
                    {
                        "in": "path",
                        "name": "fileName",
                        "required": true,
                        "type": "string",
                        "description": "Tên của tệp cần tải"
                    }
                ],
                responses: {
                    200: {
                        description: "Trả về tệp đã tải",
                        schema: {
                            type: "string",
                            format: "binary"
                        }
                    },
                    404: {
                        description: "Tệp không tìm thấy"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        },
        "/uploadfile/uploadfile": {    // Đường dẫn cho tải lên tệp
            post: {
                tags: ["uploadFile"],
                summary: "Tải lên tệp",
                description: "Tải lên tệp lên server.",
                operationId: "uploadFile",
                consumes: ["multipart/form-data"],    // Loại dữ liệu gửi đi
                produces: ["application/json"],    // Loại dữ liệu trả về
                parameters: [
                    {
                        "in": "formData",
                        "name": "file",
                        "required": true,
                        "type": "file",
                        "description": "Tệp cần tải lên"
                    }
                ],
                responses: {
                    200: {
                        description: "Tệp đã được tải lên thành công",
                        schema: {
                            type: "string",
                            example: "Tệp đã được tải lên thành công."
                        }
                    },
                    400: {
                        description: "Yêu cầu không hợp lệ"
                    },
                    500: {
                        description: "Lỗi hệ thống"
                    }
                },
                security: []
            }
        }
    }
};