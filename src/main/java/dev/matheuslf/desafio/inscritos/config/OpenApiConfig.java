package dev.matheuslf.desafio.inscritos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Gerenciamento de Projetos e Tarefas")
                .version("1.0.0")
                .description(
                    "API REST para gerenciar projetos e tarefas. " +
                    "\n\n**Funcionalidades:**" +
                    "\n- Criar, listar e gerenciar projetos" +
                    "\n- Criar, listar, atualizar e deletar tarefas" +
                    "\n- Filtrar tarefas por status, prioridade e projeto" +
                    "\n- Paginação e ordenação de projetos" +
                    "\n\n**⚠️ IMPORTANTE - Como usar o parâmetro 'sort':**" +
                    "\n- ✅ **CORRETO:** `?sort=name,asc` ou `?sort=id,desc`" +
                    "\n- ❌ **ERRADO:** `?sort=[\"string\"]` (não use o exemplo padrão do Swagger!)" +
                    "\n- Para ordenar de forma crescente: `?sort=name` ou `?sort=name,asc`" +
                    "\n- Para ordenar de forma decrescente: `?sort=name,desc`" +
                    "\n- Para múltiplas ordenações: `?sort=priority,desc&sort=dueDate,asc`" +
                    "\n\n**Propriedades válidas para ordenação:**" +
                    "\n- Projetos: `id`, `name`, `description`, `startDate`, `endDate`" +
                    "\n- Tarefas: `id`, `title`, `description`, `status`, `priority`, `dueDate`"
                )
                .contact(new Contact()
                    .name("API Support")
                    .url("https://github.com/tomas-barros1")
                )
            );
    }

    @Bean
    public OpenApiCustomizer sortParameterCustomizer() {
        return openApi -> openApi.getPaths().values().forEach(pathItem -> 
            pathItem.readOperations().forEach(operation -> {
                if (operation.getParameters() != null) {
                    operation.getParameters().forEach(parameter -> {
                        if ("sort".equals(parameter.getName())) {
                            parameter.setExample("name,asc");
                            parameter.setDescription(
                                "Ordenação no formato: propriedade,direção. " +
                                "Exemplos: 'name,asc', 'id,desc'. " +
                                "⚠️ NÃO use o exemplo '[\"string\"]'!"
                            );
                            if (parameter.getSchema() != null) {
                                parameter.getSchema().setExample("name,asc");
                            }
                        }
                    });
                }
            })
        );
    }
}
