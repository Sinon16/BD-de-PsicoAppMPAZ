-- 1. USUARIOS
INSERT INTO usuarios (username, password, nombre_completo, rol, fecha_creacion) 
VALUES 
('psico_maria', '$2a$10$e.vS2G6p3.s1BvN0PqO4p.zW5gM4N4pYgE8R4e4u5I6a7B8c9D0e1', 'Dra. María Paz Fernández', 'PSICOLOGO', CURRENT_TIMESTAMP),
('profe_juan', '$2a$10$e.vS2G6p3.s1BvN0PqO4p.zW5gM4N4pYgE8R4e4u5I6a7B8c9D0e1', 'Prof. Juan Pérez', 'PROFESOR', CURRENT_TIMESTAMP);

-- 2. ESTUDIANTES
INSERT INTO estudiantes (nombre, curso, es_temporal) 
VALUES 
('Lucas Silva', '4° Básico A', false),
('Sofía Morales', '4° Básico A', false),
('Mateo Gómez', '5° Básico B', false),
('Invitado Evaluación', 'Sin Curso', true);

-- 3. CUESTIONARIOS
INSERT INTO cuestionarios (titulo, descripcion, instrucciones_profesor, especificaciones_tecnicas_psicologo, rango_edad, tiempo_estimado_minutos) 
VALUES 
(
  'Cuestionario de Habilidades Socioemocionales', 
  'Evaluación para medir la adaptación socioemocional del estudiante en el aula.', 
  'Asegúrese de que el estudiante se encuentre en un ambiente tranquilo.', 
  'Escala Likert de 5 puntos (1: Muy mal a 5: Muy bien).', 
  '8-12 años', 
  15
);

-- 4. PREGUNTAS
INSERT INTO preguntas (enunciado, orden, cuestionario_id) 
VALUES 
('¿Cómo te sientes al trabajar en equipo con tus compañeros?', 1, 1),
('¿Qué tan fácil te resulta expresar lo que sientes cuando te molestas?', 2, 1),
('¿Sientes que el profesor escucha tus dudas durante las clases?', 3, 1);

-- 5. APLICACIONES DE EVALUACIÓN (Columna evaluador_id alineada con @JoinColumn)
INSERT INTO aplicaciones_evaluacion (
  codigo_temporal, estado, fecha_inicio, fecha_finalizacion, fecha_revision, 
  puntaje_total, interpretacion_puntaje, observaciones_psicologo, indicaciones_para_profesor, 
  estudiante_id, cuestionario_id, evaluador_id
) 
VALUES 
(
  'EST-101', 
  'COMPLETADA', 
  PARSEDATETIME('2026-11-10 10:00:00', 'yyyy-MM-dd HH:mm:ss'), 
  PARSEDATETIME('2026-11-10 10:12:00', 'yyyy-MM-dd HH:mm:ss'), 
  PARSEDATETIME('2026-11-10 11:30:00', 'yyyy-MM-dd HH:mm:ss'), 
  85, 
  'Desempeño Socioemocional Promedio Alto', 
  'El estudiante muestra ligera ansiedad en respuestas de interacción social.', 
  'Promover actividades grupales estructuradas donde el alumno asuma un rol activo.', 
  1, 1, 1
);

-- 6. RESPUESTAS DETALLE
INSERT INTO respuestas_detalle (opcion_seleccionada, valor_puntaje, fecha_respuesta, pregunta_id, aplicacion_id) 
VALUES 
('Bien', 25, PARSEDATETIME('2026-11-10 10:02:00', 'yyyy-MM-dd HH:mm:ss'), 1, 1),
('Medio', 15, PARSEDATETIME('2026-11-10 10:05:00', 'yyyy-MM-dd HH:mm:ss'), 2, 1),
('Muy bien', 30, PARSEDATETIME('2026-11-10 10:11:00', 'yyyy-MM-dd HH:mm:ss'), 3, 1);

-- 7. ASISTENCIAS
INSERT INTO asistencias (fecha, presente, estudiante_id, registrado_por_id) 
VALUES 
(CURRENT_DATE(), true, 1, 2),
(CURRENT_DATE(), false, 2, 2);