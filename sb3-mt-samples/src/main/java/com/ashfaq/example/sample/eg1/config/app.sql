SELECT id, category, item_name, price
FROM springbootdev.carts;

SELECT upload_id, status, `timestamp`
FROM springbootdev.cart_uploads;


SELECT id, cart_id, comment, status, upload_id
FROM cart_upload_item_statuses;

SELECT id, cart_id, comment, status, upload_id
FROM cart_upload_item_statuses where upload_id = 'e61210c2-aef0-4e68-9c2b-caae5e64911f';


commit;
SELECT id, cart_id, comment, status, upload_id
FROM cart_upload_item_statuses where upload_id = 'ASYNC - 51606f1f-4d26-4f6f-82db-849594bb9474';