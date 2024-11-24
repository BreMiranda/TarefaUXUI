# Projeto: Teste de Caixa Branca
# Materia: UX UI


# Descrição
Este projeto foi desenvolvido para análise de código com base em critérios de **Teste de Caixa Branca**. O código Java simula uma verificação de login com um banco de dados MySQL.

---

## Erros Identificados

1. **Ausência de Fechamento da Conexão:**
   - O método `conectarBD()` abre uma conexão com o banco, mas não a fecha, o que pode causar vazamentos de recursos.

2. **Risco de SQL Injection:**
   - No método `verificarUsuario`, a query SQL é construída concatenando diretamente os parâmetros `login` e `senha`. Isso permite ataques de **SQL Injection**.

3. **Falta de Validação da Conexão:**
   - Não há verificação para garantir que a conexão com o banco foi estabelecida com sucesso antes de executar comandos.

4. **Uso de `Statement` em vez de `PreparedStatement`:**
   - `PreparedStatement` é mais seguro, pois evita SQL Injection ao tratar parâmetros.

5. **Captura Genérica de Exceções:**
   - As exceções capturadas são genéricas e não são registradas em logs. Isso dificulta o diagnóstico de erros.

---

## Soluções Recomendadas

1. Utilize `try-with-resources` para garantir que a conexão com o banco seja fechada automaticamente.
2. Substitua o `Statement` por `PreparedStatement` para evitar SQL Injection.
3. Valide se a conexão foi bem-sucedida antes de realizar operações no banco de dados.
4. Adicione logs para registrar exceções e facilitar o diagnóstico.
5. Atualize a URL do banco de dados e as credenciais conforme necessário.
