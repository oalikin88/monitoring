import js from "@eslint/js";
import globals from "globals";

export default [
  // 1. Игнорируем лишние папки (аналог .eslintignore)
  {
    ignores: [
        "**/target/**", 
        "**/build/**", 
        "**/node_modules/**",
        "**/dist/**"
    ],
  },
  
  // 2. Основная конфигурация
  {
    files: ["**/*.js"],
    languageOptions: {
      ecmaVersion: "latest", // поддержка современного JS
      sourceType: "module",
      globals: {
        ...globals.browser, // добавляет window, document, fetch и т.д.
        ...globals.jquery,  // добавьте, если используете jQuery
      },
    },
    rules: {
      ...js.configs.recommended.rules, // базовые правила ESLint
      "no-unused-vars": "warn",        // предупреждать о неиспользуемых переменных
      "no-console": "off",             // разрешить console.log (удобно при разработке)
      "eqeqeq": "error",               // требовать строгое сравнение ===
      "curly": "error",                // обязательные фигурные скобки для if/for
    },
  },
];