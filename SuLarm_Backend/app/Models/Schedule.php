<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Schedule extends Model
{
    use HasFactory;
    protected $fillable = [
        'time',
        'arrived_before',
        'estimated_travel_time',
        'preparation_time',
        'location_end',
        'end_coor',
        'location_start',
        'start_coor',
        'status'
    ];
}
