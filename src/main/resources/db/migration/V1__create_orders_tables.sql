CREATE TABLE orders (
    id     uuid PRIMARY KEY,
    status varchar(32) NOT NULL
);

CREATE TABLE order_items (
    order_id   uuid NOT NULL,
    product_id uuid NOT NULL,
    quantity  integer NOT NULL CHECK (quantity > 0),

    PRIMARY KEY (order_id, product_id),

    CONSTRAINT fk_order_items_order  --(RULE)
             FOREIGN KEY (order_id)
             REFERENCES orders(id) --PARENT
)