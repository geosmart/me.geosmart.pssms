var gulp = require('gulp');
var less = require('gulp-less');
var header = require('gulp-header');
var cleanCSS = require('gulp-clean-css');
var rename = require("gulp-rename");
var uglify = require('gulp-uglify');
var pkg = require('./package.json');

// Set the banner content
var banner = ['/*!\n',
    ' * Start Bootstrap - <%= pkg.title %> v<%= pkg.version %> (<%= pkg.homepage %>)\n',
    ' * Copyright 2013-' + (new Date()).getFullYear(), ' <%= pkg.author %>\n',
    ' * Licensed under <%= pkg.license.type %> (<%= pkg.license.url %>)\n',
    ' */\n',
    ''
].join('');

// Compile LESS files from /less into /css
gulp.task('less', function() {
    return gulp.src('less/sb-admin-2.less')
        .pipe(less())
        .pipe(header(banner, { pkg: pkg }))
        .pipe(gulp.dest('dist/css'))
});

// Minify compiled CSS
gulp.task('minify-css', ['less'], function() {
    return gulp.src('dist/css/sb-admin-2.css')
        .pipe(cleanCSS({ compatibility: 'ie8' }))
        .pipe(rename({ suffix: '.min' }))
        .pipe(gulp.dest('dist/css'))
});

// Copy JS to dist
gulp.task('js', function() {
    return gulp.src(['js/sb-admin-2.js'])
        .pipe(header(banner, { pkg: pkg }))
        .pipe(gulp.dest('dist/js'))
})

// Minify JS
gulp.task('minify-js', ['js'], function() {
    return gulp.src('js/sb-admin-2.js')
        .pipe(uglify())
        .pipe(header(banner, { pkg: pkg }))
        .pipe(rename({ suffix: '.min' }))
        .pipe(gulp.dest('dist/js'))
});

// Copy vendor libraries from /node_modules into /vendor
gulp.task('copy', function() {
    gulp.src(['node_modules/bootstrap-table/dist/*'])
        .pipe(gulp.dest('vendor/bootstrap-table'))

    gulp.src(['node_modules/bootstrap/dist/**/*', '!**/npm.js', '!**/bootstrap-theme.*', '!**/*.map'])
        .pipe(gulp.dest('vendor/bootstrap'))

    gulp.src(['node_modules/bootstrap-social/*.css', 'node_modules/bootstrap-social/*.less', 'node_modules/bootstrap-social/*.scss'])
        .pipe(gulp.dest('vendor/bootstrap-social'))

    gulp.src(['node_modules/datatables/media/**/*'])
        .pipe(gulp.dest('vendor/datatables'))

    gulp.src(['node_modules/datatables-plugins/integration/bootstrap/3/*'])
        .pipe(gulp.dest('vendor/datatables-plugins'))

    gulp.src(['node_modules/datatables-responsive/css/*', 'node_modules/datatables-responsive/js/*'])
        .pipe(gulp.dest('vendor/datatables-responsive'))

    gulp.src(['node_modules/flot/*.js'])
        .pipe(gulp.dest('vendor/flot'))

    gulp.src(['node_modules/flot.tooltip/js/*.js'])
        .pipe(gulp.dest('vendor/flot-tooltip'))

    gulp.src(['node_modules/font-awesome/**/*', '!node_modules/font-awesome/*.json', '!node_modules/font-awesome/.*'])
        .pipe(gulp.dest('vendor/font-awesome'))

    gulp.src(['node_modules/jquery/dist/jquery.js', 'node_modules/jquery/dist/jquery.min.js'])
        .pipe(gulp.dest('vendor/jquery'))

    gulp.src(['node_modules/metisMenu/dist/*'])
        .pipe(gulp.dest('vendor/metisMenu'))

    gulp.src(['node_modules/morrisjs/*.js', 'node_modules/morrisjs/*.css', '!node_modules/morrisjs/Gruntfile.js'])
        .pipe(gulp.dest('vendor/morrisjs'))

    gulp.src(['node_modules/raphael/raphael.js', 'node_modules/raphael/raphael.min.js'])
        .pipe(gulp.dest('vendor/raphael'))

})

// Run everything
gulp.task('default', ['minify-css', 'minify-js', 'copy']);

// Configure the browserSync task
// gulp.task('browserSync', function() {
//     browserSync.init({
//         server: {
//             baseDir: ''
//         },
//     })
// })

// Dev task with browserSync
gulp.task('dev', ['browserSync', 'less', 'minify-css', 'js', 'minify-js'], function() {
    gulp.watch('less/*.less', ['less']);
    gulp.watch('dist/css/*.css', ['minify-css']);
    gulp.watch('js/*.js', ['minify-js']);
    // Reloads the browser whenever HTML or JS files change
    // gulp.watch('pages/*.html', browserSync.reload);
    // gulp.watch('dist/js/*.js', browserSync.reload);
});
