<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('schedules', function (Blueprint $table) {
            $table->id()->autoIncrement();
            $table->time("time");
            $table->time("arrived_before");
            $table->time("estimated_travel_time");
            $table->string("location_end",255);
            $table->string("end_coor",255);
            $table->string("location_start",255);
            $table->string("start_coor",255);
            $table->boolean("status");
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('schedules');
    }
};
