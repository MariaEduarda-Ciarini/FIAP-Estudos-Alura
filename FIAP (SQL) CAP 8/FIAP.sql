INSERT INTO T_PRODUTO (
                CD_PRODUTO,
                NM_PRODUTO,
                VL_PRODUTO,
                DT_VALIDADE
        )
VALUES (
                SEQ_PRODUTO.NEXTVAL,
                'MAÇA',
                7,
                TO_DATE('27/03/2024', 'DD/MM/YYYY')
        );
UPDATE T_PRODUTO
SET VL_PRODUTO = 9.99
WHERE CD_PRODUTO = 1;
SELECT CD_PRODUTO,
        NM_PRODUTO,
        VL_PRODUTO,
        DT_VALIDADE
FROM T_PRODUTO;
DELETE FROM T_PRODUTO
WHERE CD_PRODUTO = 1;