package cn.tjpuacm.pcregister.util.api;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import lombok.NoArgsConstructor;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 根据Swagger生成离线文档
 *
 * @author ningxy
 * @date 2018-11-15 16:11
 */
@NoArgsConstructor
public class ApiDocGenerator {
    /**
     * 默认生成文件类型
     */
    private static final MarkupLanguage DEFAULT_MARKUP_LANGUAGE = MarkupLanguage.MARKDOWN;

    /**
     * 默认生成文件方式
     */
    private static final GenerationType DEFAULT_GENERATION_TYPE = GenerationType.AMALGAMATION;

    /**
     * Swagger API URL
     */
    private URL apiUrl;

    /**
     * 输出文件目录
     */
    private Path outputLoc;

    /**
     * 生成文件方式
     */
    private GenerationType generationType;

    /**
     * 生成文件类型
     */
    private MarkupLanguage markupLanguage;

    public static void main(String[] args) throws Exception {
        new ApiDocGenerator.DocBuilder()
                .setApiUrl("http://localhost:8082/tjpupc/v2/api-docs")
                .setOutputLoc("src/docs/api/generated")
                .setMarkupLanguage(MarkupLanguage.MARKDOWN)
                .setGenerationType(GenerationType.AMALGAMATION)
                .build();
    }

    private static class DocBuilder {

        private ApiDocGenerator docGen = new ApiDocGenerator();

        private DocBuilder setMarkupLanguage(MarkupLanguage markupLanguage) {
            docGen.markupLanguage = markupLanguage;
            return this;
        }

        private DocBuilder setGenerationType(GenerationType generationType) {
            docGen.generationType = generationType;
            return this;
        }

        private DocBuilder setApiUrl(String url) throws MalformedURLException {
            docGen.apiUrl = new URL(url);
            return this;
        }

        private DocBuilder setOutputLoc(String outputLoc) {
            docGen.outputLoc = Paths.get(outputLoc);
            return this;
        }

        private void build() throws Exception {
            MarkupLanguage markupLanguage = docGen.markupLanguage;
            if (markupLanguage == null) {
                markupLanguage = DEFAULT_MARKUP_LANGUAGE;
            }

            if (docGen.generationType == null) {
                docGen.generationType = DEFAULT_GENERATION_TYPE;
            }

            if (docGen.outputLoc == null || docGen.apiUrl == null) {
                throw new RuntimeException("参数不完整");
            }

            Swagger2MarkupConfig config = generateMarkupConfig(markupLanguage);
            Swagger2MarkupConverter converter = generateMarkupConverter(config);
            convert(converter);
        }

        private Swagger2MarkupConfig generateMarkupConfig(MarkupLanguage markupLanguage) {
            return new Swagger2MarkupConfigBuilder()
                    .withMarkupLanguage(markupLanguage)
                    .withOutputLanguage(Language.ZH)
                    .withPathsGroupedBy(GroupBy.TAGS)
                    .withGeneratedExamples()
                    .withoutInlineSchema()
                    .build();
        }

        private Swagger2MarkupConverter generateMarkupConverter(Swagger2MarkupConfig config) throws MalformedURLException {
            return Swagger2MarkupConverter.from(docGen.apiUrl)
                    .withConfig(config)
                    .build();
        }

        /**
         * 转换生成文件
         *
         * @param converter Swagger2MarkupConverter
         */
        private void convert(Swagger2MarkupConverter converter) {
            final GenerationType generationType = docGen.generationType;
            final Path outputLoc = docGen.outputLoc;

            switch (generationType) {
                case SEPARATION:
                    // 各部分分开生成
                    converter.toFolder(outputLoc);
                    break;
                case AMALGAMATION:
                    // 合并生成一个文件
                    converter.toFile(outputLoc);
                    break;
                default:
            }
        }
    }
}

/**
 * 生成文件形式的枚举类
 */
enum GenerationType {
    /**
     * 各部分分开生成
     */
    SEPARATION,

    /**
     * 合并生成一个文件
     */
    AMALGAMATION
}
