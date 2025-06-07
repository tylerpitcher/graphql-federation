const { createEnv } = require("@t3-oss/env-nextjs");
const { z } = require("zod");

const env = createEnv({
  server: {
    COURSES_ENDPOINT: z.string().min(1),
    STUDENTS_ENDPOINT: z.string().min(1),
  },

  runtimeEnv: {
    COURSES_ENDPOINT: process.env.COURSES_ENDPOINT,
    STUDENTS_ENDPOINT: process.env.STUDENTS_ENDPOINT,
  },
});

module.exports = env;
